package com.melita.OrderService.event;

import com.melita.OrderService.model.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class OrderEventPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final String exchangeName;
    private final String routingKey;

    public OrderEventPublisher(RabbitTemplate rabbitTemplate,
                               @Value("${rabbitmq.exchange.name}") String exchangeName,
                               @Value("${rabbitmq.routing.key}") String routingKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchangeName = exchangeName;
        this.routingKey = routingKey;
    }

    public void publishOrderCreatedEvent(Order order) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, order);
    }
}
