package com.example.SushiStore.Service.implementations;

import com.example.SushiStore.Entity.Roles;
import com.example.SushiStore.Entity.Users;
import com.example.SushiStore.Repositories.RoleRepository;
import com.example.SushiStore.Repositories.UsersRepository;
import com.example.SushiStore.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Users myUser=usersRepository.findByEmail(s);
        if (myUser!=null){
            User secUser=new User(myUser.getEmail(),myUser.getPassword(),myUser.getRoles());
            return secUser;
        }

        throw new  UsernameNotFoundException("User not found!");
    }

    @Override
    public Users getUserByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    @Override
    public Users createUser(Users user) {
        Users checkUser=usersRepository.findByEmail(user.getEmail());
        if (checkUser==null){
            Roles role=roleRepository.findByRole("ROLE_USER");
            if (role!=null){
                ArrayList<Roles>roles=new ArrayList<>();
                roles.add(role);
                user.setRoles(roles);
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                return usersRepository.save(user);
            }
        }
        return null;
    }

    @Override
    public Users saveUser(Users user) {
        return usersRepository.save(user);
    }

    @Override
    public ArrayList<Users> getAllUsers(){
         return usersRepository.findAll();
    }

    @Override
    public Users getUserById(Long id) {
        return usersRepository.getById(id);
    }

    @Override
    public Boolean deleteUser(Long id) {
        Users user=usersRepository.getById(id);
        if (user!=null){
            usersRepository.delete(user);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Roles findRole(String role) {
        return roleRepository.findByRole(role);
    }

    @Override
    public List<Roles> getAllRoles() {
        return roleRepository.findAll();
    }
}
