package com.rabbitmq.message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiverFirst {

    @RabbitListener(queues = "TOPIC.FIRST.QUEUE")
    public void handleMessage(String message) {
        System.out.println("TOPIC.FIRST.QUEUE Received: " + message);
    }
}