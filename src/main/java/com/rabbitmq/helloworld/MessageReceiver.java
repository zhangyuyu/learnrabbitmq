package com.rabbitmq.helloworld;

import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

    public void handleMessage(String message) {
        System.out.println("Received: " + message);
    }
}