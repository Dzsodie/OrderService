package com.melita.OrderService.service;

import com.melita.OrderService.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    public Order placeOrder(Order order);
    public List<Order> fetchOrdersFromERP();
}
