package com.rabbitmq.message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiverSecond {

    @RabbitListener(queues = "FANOUT.SECOND.QUEUE")
    public void handleMessage(String message) {
        System.out.println(" FANOUT.SECOND.QUEUE Received: " + message);
    }
}