package org.example.demorebbitmq.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.example.demorebbitmq.AppConstants.*;

@Component
public class SenderMessage {

    private final AmqpTemplate amqpTemplate;

    @Autowired
    public SenderMessage(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void sendMessage(String message) {
        // Specify the exchange name and routing key
        amqpTemplate.convertAndSend(MY_FIRST_QUEUE_EXCHANGE_NAME, null, message);
        amqpTemplate.convertAndSend(MY_SECOND_QUEUE_EXCHANGE_NAME, null, message);
    }
}
