package com.rabbitmq.message;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    @Autowired
    private RabbitTemplate template;

    public void send(String routingKey, String message) {
        template.convertAndSend(routingKey, message);
        System.out.println("Sent: " + message);
    }
}