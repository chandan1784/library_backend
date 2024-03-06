package com.studyroom.notification.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    private static final String QUEUE_NAME = "user_queue";
    private static final String EXCHANGE_NAME = "amq.topic"; // This should match the exchange name in your User microservice

    @Bean
    public Queue userQueue() {
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public Binding userQueueBinding() {
        return BindingBuilder.bind(userQueue()).to(exchange()).with("user.created");
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }
}
