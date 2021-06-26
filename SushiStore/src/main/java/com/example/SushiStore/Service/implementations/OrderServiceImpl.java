package com.example.SushiStore.Service.implementations;

import com.example.SushiStore.Entity.Order;
import com.example.SushiStore.Repositories.OrderRepository;
import com.example.SushiStore.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public ArrayList<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOneOrderById(Long id) {
        return orderRepository.getById(id);
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
          orderRepository.delete(getOneOrderById(id));
    }
}
