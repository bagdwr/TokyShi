package com.example.SushiStore.Repositories;

import com.example.SushiStore.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Repository
@Transactional
public interface UsersRepository extends JpaRepository<Users,Long> {
    Users findByEmail(String email);
    ArrayList<Users> findAll();
    Users getById(Long id);
}
