package com.example.SushiStore.Repositories;

import com.example.SushiStore.Entity.Sets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Repository
@Transactional
public interface SetsRepository extends JpaRepository<Sets,Long> {
    ArrayList<Sets>findAll();
    Sets getById(Long id);
}
