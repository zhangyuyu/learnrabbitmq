package com.rabbitmq.controller;

import com.rabbitmq.message.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/rabbitmq")
public class RabbitmqController {

    @Autowired
    MessageSender sender;

    @RequestMapping(method = POST)
    public void sendByQueue(@RequestParam String routingKey, @RequestParam String message) {
        sender.send(routingKey, message);
    }

}
