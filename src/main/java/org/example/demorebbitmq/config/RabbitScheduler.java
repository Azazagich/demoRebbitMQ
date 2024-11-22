package org.example.demorebbitmq.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import static org.example.demorebbitmq.AppConstants.*;

@Component
@EnableScheduling
public class RabbitScheduler {

    private final static Logger LOGGER = LoggerFactory.getLogger(RabbitScheduler.class);

    private final SenderMessage senderMessage;

    @Autowired
    public RabbitScheduler(SenderMessage senderMessage){
        this.senderMessage = senderMessage;
    }

    @Scheduled(fixedRate = 10000)
    public void write() {
        LOGGER.info("Send info");
        senderMessage.sendMessage(MY_FIRST_QUEUE_EXCHANGE_NAME,
                                MY_FIRST_QUEUE_ROUTING_KEY,
                        "I'm dumb");
    }
}
