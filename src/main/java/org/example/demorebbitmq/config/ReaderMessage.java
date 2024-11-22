package org.example.demorebbitmq.config;

import org.example.demorebbitmq.AppConstants;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@EnableRabbit
public class ReaderMessage {

    @RabbitListener(queues = AppConstants.MY_FIRST_QUEUE_NAME)
    public void receiveMessageFirst(String message) {
        System.out.println("Worker1 Messasge: '" + message + "'");
    }

    @RabbitListener(queues = AppConstants.MY_FIRST_QUEUE_NAME)
    public void receiveMessageSecond(String message) {
        System.out.println("Worker2 Messasge: '" + message + "'");
    }
}
