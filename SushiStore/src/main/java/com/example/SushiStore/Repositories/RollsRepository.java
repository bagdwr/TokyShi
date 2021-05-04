package com.example.SushiStore.Repositories;

import com.example.SushiStore.Entity.Rolls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Repository
@Transactional
public interface RollsRepository extends JpaRepository<Rolls,Long> {
    ArrayList<Rolls>findAll();
    Rolls getById(Long id);
}
