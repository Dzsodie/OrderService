package com.melita.OrderService.service;

import com.melita.OrderService.event.OrderEventPublisher;
import com.melita.OrderService.model.Order;
import com.melita.OrderService.model.OrderStatus;
import com.melita.OrderService.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderEventPublisher orderEventPublisher;

    public OrderService(OrderRepository orderRepository, OrderEventPublisher orderEventPublisher) {
        this.orderRepository = orderRepository;
        this.orderEventPublisher = orderEventPublisher;
    }

    public Order placeOrder(Order order) {
        order.setStatus(OrderStatus.PENDING);
        Order savedOrder = orderRepository.save(order);
        orderEventPublisher.publishOrderEvent(savedOrder);
        return savedOrder;
    }
}
