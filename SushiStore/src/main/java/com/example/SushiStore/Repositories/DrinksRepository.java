package com.example.SushiStore.Repositories;

import com.example.SushiStore.Entity.Drinks;
import com.example.SushiStore.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface DrinksRepository extends JpaRepository<Drinks,Long> {
    ArrayList<Drinks> findAll();
    Drinks getById(Long id);
}
