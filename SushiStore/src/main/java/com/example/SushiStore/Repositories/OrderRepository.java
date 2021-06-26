package com.example.SushiStore.Repositories;

import com.example.SushiStore.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Order,Long> {
    ArrayList<Order>findAll();
    Order getById(Long id);
}
