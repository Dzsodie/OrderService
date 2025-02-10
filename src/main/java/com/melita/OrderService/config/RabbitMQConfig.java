package com.melita.OrderService.config;

import lombok.Getter;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.exchange.name}")
    private static String exchangeName;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Bean
    public static TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

    @Autowired
    public RabbitMQConfig(@Value("${rabbitmq.routing.key}") String routingKey) {
        this.routingKey = routingKey;
    }
}