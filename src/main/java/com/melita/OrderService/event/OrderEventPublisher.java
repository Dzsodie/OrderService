package com.melita.OrderService.event;

import com.melita.OrderService.config.RabbitMQConfig;
import com.melita.OrderService.model.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;


@Component
public class OrderEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public OrderEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishOrderCreatedEvent(Order order) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.getNotificationExchange(), RabbitMQConfig.getNotificationRoutingKey(), order);
    }
}
