package com.studyroom.user.message;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PublishMessage {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    private static final String exchangeName = "amq.topic";
    private static final String routingKey = "user.created";

    public void publish(String message) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
        System.out.println("Message published to RabbitMQ: " + message);
    }
}
