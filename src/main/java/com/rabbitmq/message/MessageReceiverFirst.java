package com.rabbitmq.message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiverFirst {

    @RabbitListener(queues = "HEADERS.FIRST.QUEUE")
    public void handleMessage(String message) {
        System.out.println("HEADERS.FIRST.QUEUE Received: " + message);
    }
}