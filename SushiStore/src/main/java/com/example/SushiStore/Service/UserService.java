package com.example.SushiStore.Service;

import com.example.SushiStore.Entity.Roles;
import com.example.SushiStore.Entity.Users;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.List;

public interface UserService extends UserDetailsService {
    Users getUserByEmail(String email);
    Users createUser(Users user);
    Users saveUser(Users user);
    ArrayList<Users> getAllUsers();
    Users getUserById(Long id);
    Boolean deleteUser(Long id);
    Roles findRole(String role);
    List<Roles> getAllRoles();
}
