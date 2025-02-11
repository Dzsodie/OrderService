package com.melita.OrderService.event;

import com.melita.OrderService.config.RabbitMQConfig;
import com.melita.OrderService.model.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderEventPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final String exchange;
    private final String routingKey;

    public OrderEventPublisher(RabbitTemplate rabbitTemplate, RabbitMQConfig rabbitMQConfig) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = rabbitMQConfig.getNotificationExchange();
        this.routingKey = rabbitMQConfig.getNotificationRoutingKey();
    }

    public void publishOrderCreatedEvent(Order order) {
        rabbitTemplate.convertAndSend(exchange, routingKey, order);
    }
}
