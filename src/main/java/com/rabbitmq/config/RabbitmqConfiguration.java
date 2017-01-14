package com.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.AbstractConnectionFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableRabbit
public class RabbitmqConfiguration {

    @Value("${headers.first.queue.name}")
    public String headersFirstQueue;

    @Value("${headers.second.queue.name}")
    public String headersSecondQueue;

    @Value("${headers.exchange.name}")
    public String exchangeName;

    @Value("${rabbitmq.host}")
    public String host;

    @Value("${rabbitmq.username}")
    public String username;

    @Value("${rabbitmq.password}")
    public String password;

    @Bean
    public Queue headersFirstQueue() {
        return new Queue(headersFirstQueue);
    }

    @Bean
    public Queue headersSecondQueue() {
        return new Queue(headersSecondQueue);
    }

    @Bean
    public HeadersExchange exchange() {
        return new HeadersExchange(exchangeName);
    }

    @Bean
    Binding bindingFirstQueue(@Qualifier("headersFirstQueue") Queue queue, HeadersExchange exchange) {
        Map<String, Object> map = new HashMap<>();
        map.put("type", "headers");
        map.put("number", "first");
        return BindingBuilder.bind(queue).to(exchange).whereAny(map).match();
    }

    @Bean
    Binding bindingSecondQueue(@Qualifier("headersSecondQueue") Queue queue, HeadersExchange exchange) {
        Map<String, Object> map = new HashMap<>();
        map.put("type", "headers");
        map.put("number", "second");
        return BindingBuilder.bind(queue).to(exchange).whereAll(map).match();
    }

    @Bean
    AbstractConnectionFactory connectionFactory() {
        com.rabbitmq.client.ConnectionFactory factory = new com.rabbitmq.client.ConnectionFactory();
        factory.setHost(host);
        factory.setUsername(username);
        factory.setPassword(password);
        return new CachingConnectionFactory(factory);
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setExchange(exchangeName);
        return template;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        return factory;
    }

}
