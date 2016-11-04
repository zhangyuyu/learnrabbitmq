package com.rabbitmq.controller;

import com.rabbitmq.helloworld.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/rabbitmq")
public class RabbitmqController {

    @Autowired
    MessageSender sender;

    @RequestMapping(value = "/message/{message}", method = POST)
    public void sendByQueue(@PathVariable String message) {
        sender.send(message);
    }

}
