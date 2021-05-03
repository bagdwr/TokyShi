package com.example.SushiStore.Repositories;

import com.example.SushiStore.Entity.Drinks;
import com.example.SushiStore.Entity.Sushi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public interface SushiRepository extends JpaRepository<Sushi, Long> {
    ArrayList<Sushi> findAll();
    Sushi getById(Long id);
}
