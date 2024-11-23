package org.example.demorebbitmq.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class RabbitScheduler {

    private final static Logger LOGGER = LoggerFactory.getLogger(RabbitScheduler.class);

    private final SenderMessage senderMessage;

    @Autowired
    public RabbitScheduler(SenderMessage senderMessage){
        this.senderMessage = senderMessage;
    }

    @Scheduled(fixedRate = 100000)
    public void write() {
        LOGGER.info("Send info");
        senderMessage.sendMessage("I'm stupid!!!", "route.one.test");
        senderMessage.sendMessage("I'm soooooooooooooo dumb", "route.two.anything");
    }
}
