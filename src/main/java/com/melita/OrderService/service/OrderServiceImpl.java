package com.melita.OrderService.service;

import com.melita.OrderService.event.OrderEventPublisher;
import com.melita.OrderService.model.Order;
import com.melita.OrderService.model.OrderStatus;
import com.melita.OrderService.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderEventPublisher orderEventPublisher;
    private final RestTemplate restTemplate;

    @Value("${erp.api.url}")
    private String erpApiUrl;

    @Value("${erp.api.key}")
    private String erpApiKey;

    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderEventPublisher orderEventPublisher,
                            RestTemplateBuilder restTemplateBuilder) {
        this.orderRepository = orderRepository;
        this.orderEventPublisher = orderEventPublisher;
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Order placeOrder(Order order) {
        order.setStatus(OrderStatus.PENDING);
        Order savedOrder = orderRepository.save(order);
        orderEventPublisher.publishOrderCreatedEvent(savedOrder);
        return savedOrder;
    }

    @Override
    public List<Order> fetchOrdersFromERP() {
        try {
            Order[] ordersArray = restTemplate.getForObject(
                    erpApiUrl + "/orders?status=created&apiKey=" + erpApiKey,
                    Order[].class
            );

            if (ordersArray == null) {
                throw new RuntimeException("Failed to fetch orders from ERP");
            }

            List<Order> orders = Arrays.stream(ordersArray)
                    .map(this::processFetchedOrder)
                    .collect(Collectors.toList());

            return orderRepository.saveAll(orders);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching orders from ERP: " + e.getMessage(), e);
        }
    }

    private Order processFetchedOrder(Order order) {
        order.setStatus(OrderStatus.PENDING);
        orderEventPublisher.publishOrderCreatedEvent(order);
        return order;
    }
}
