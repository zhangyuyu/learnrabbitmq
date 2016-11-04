package com.rabbitmq.helloworld;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    @Autowired
    private RabbitTemplate template;

    public void send(String message) {
        template.convertAndSend(message);
        System.out.println("Sent: " + message);

    }
}