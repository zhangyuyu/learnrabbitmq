package com.rabbitmq.message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiverSecond {

    @RabbitListener(queues = "TOPIC.SECOND.QUEUE")
    public void handleMessage(String message) {
        System.out.println("TOPIC.SECOND.QUEUE Received: " + message);
    }
}