package com.example.SushiStore.Service;

import com.example.SushiStore.Entity.Order;

import java.util.ArrayList;

public interface OrderService {
    ArrayList<Order>getAllOrders();
    Order getOneOrderById(Long Id);
    Order createOrder(Order order);
    Order saveOrder(Order order);
    void deleteOrder(Long id);
}
