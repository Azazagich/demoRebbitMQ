package org.example.demorebbitmq.config;

import jakarta.annotation.PostConstruct;
import org.example.demorebbitmq.AppConstants;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import static org.example.demorebbitmq.AppConstants.*;

@Configuration
@EnableRabbit
public class RabbitConfig {

    private final AmqpAdmin rabbitAdmin;

    @Autowired
    public RabbitConfig(AmqpAdmin rabbitAdmin) {
        this.rabbitAdmin = rabbitAdmin;
    }

    @PostConstruct
    public void declareQueue() {

        boolean durable = true;

        boolean exclusive = false;

        boolean autoDelete = false;

        // Declare the exchange first
        DirectExchange exchange = new DirectExchange(
                MY_FIRST_QUEUE_EXCHANGE_NAME, durable, autoDelete);
        rabbitAdmin.declareExchange(exchange);

        // Declare the queue
        String queueName = rabbitAdmin.declareQueue(
                new Queue(MY_FIRST_QUEUE_NAME, durable, exclusive, autoDelete));

        // Bind the queue to the exchange with the routing key
        Binding binding = new Binding(
                queueName,
                Binding.DestinationType.QUEUE,
                MY_FIRST_QUEUE_EXCHANGE_NAME,
                MY_FIRST_QUEUE_ROUTING_KEY,
                null);

        rabbitAdmin.declareBinding(binding);
    }
}
