package com.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.AbstractConnectionFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitmqConfiguration {

    @Value("${topic.first.queue.name}")
    public String topicFirstQueue;

    @Value("${topic.second.queue.name}")
    public String topicSecondQueue;

    @Value("${topic.exchange.name}")
    public String exchangeName;

    @Value("${rabbitmq.host}")
    public String host;

    @Value("${rabbitmq.username}")
    public String username;

    @Value("${rabbitmq.password}")
    public String password;

    @Bean
    public Queue topicFirstQueue() {
        return new Queue(topicFirstQueue);
    }

    @Bean
    public Queue topicSecondQueue() {
        return new Queue(topicSecondQueue);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean
    Binding bindingFirstQueue(@Qualifier("topicFirstQueue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("routing.key.for.*.queue");
    }

    @Bean
    Binding bindingSecondQueue(@Qualifier("topicSecondQueue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("routing.key.for.second.queue");
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
