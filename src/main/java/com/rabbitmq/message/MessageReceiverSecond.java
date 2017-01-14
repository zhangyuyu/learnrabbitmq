package com.rabbitmq.message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiverSecond {

    @RabbitListener(queues = "DIRECT.SECOND.QUEUE")
    public void handleMessage(String message) {
        System.out.println("DIRECT.SECOND.QUEUE Received: " + message);
    }
}