package org.example.demorebbitmq.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import static org.example.demorebbitmq.AppConstants.*;

//* (star) can substitute for exactly one word.
//# (hash) can substitute for zero or more words.

@Component
public class SenderMessage {

    private final AmqpTemplate amqpTemplate;

    @Autowired
    public SenderMessage(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void sendMessage(String message, String routineKey) {
        // Specify the exchange name and routing key
        amqpTemplate.convertAndSend(MY_QUEUE_EXCHANGE_NAME, routineKey, message);
        amqpTemplate.convertAndSend(MY_QUEUE_EXCHANGE_NAME, routineKey, message);
    }
}