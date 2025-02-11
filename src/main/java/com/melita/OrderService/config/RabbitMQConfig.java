package com.melita.OrderService.config;

import lombok.Getter;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;


@Configuration
public class RabbitMQConfig {

    @Getter
    @Value("${rabbitmq.exchange.name}")
    private static String exchangeName;

    @Getter
    @Value("${rabbitmq.routing.key}")
    private static String routingKey;

    @Bean
    public static TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }
}