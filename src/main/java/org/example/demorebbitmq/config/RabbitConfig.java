package org.example.demorebbitmq.config;

import jakarta.annotation.PostConstruct;
import org.example.demorebbitmq.AppConstants;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.FanoutExchangeParser;
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
        TopicExchange firstExchange = new TopicExchange(MY_QUEUE_EXCHANGE_NAME, durable, autoDelete);
        //TopicExchange secondExchange = new TopicExchange(MY_FIRST_QUEUE_EXCHANGE_NAME, durable, autoDelete);

        rabbitAdmin.declareExchange(firstExchange);
        //rabbitAdmin.declareExchange(secondExchange);

        // Declare the queue
        Queue firstQueue = new Queue(MY_FIRST_QUEUE_NAME, durable, exclusive, autoDelete);
        Queue secondQueue = new Queue(MY_SECOND_QUEUE_NAME, durable, exclusive, autoDelete);

        rabbitAdmin.declareQueue(firstQueue);
        rabbitAdmin.declareQueue(secondQueue);

        // Bind the queue to the exchange with the routing key
        rabbitAdmin.declareBinding(BindingBuilder.bind(firstQueue).to(firstExchange).with("route.one.*"));
        rabbitAdmin.declareBinding(BindingBuilder.bind(secondQueue).to(firstExchange).with("route.two.#"));
    }
}
