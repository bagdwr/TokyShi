package com.example.SushiStore.Repositories;

import com.example.SushiStore.Entity.Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Repository
@Transactional
public interface IngredientsRepository extends JpaRepository<Ingredients,Long> {
    ArrayList<Ingredients>findAll();
    Ingredients getById(Long id);
}
