package com.studyroom.notification.message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.studyroom.notification.service.NotificationService;

@Component
public class MessageListener {
	
	@Autowired
	private NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queueName}")
    public void receiveMessage(String message) {
        // Implement your logic to handle the received message
		if (message.equalsIgnoreCase("User Added Successfully")) {
			System.out.println("Received message from RabbitMQ: " + message);
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

			simpleMailMessage.setFrom("ck17840@gmail.com");

			simpleMailMessage.setTo("chandankumar01784@gmail.com");

			simpleMailMessage.setSubject("Welcome to Study Corner - Your Seat Booking Registration is Successful!");
			simpleMailMessage.setText(
					"Congratulations! You've successfully registered with our App for seat booking. We're delighted to have you on board and look forward to serving you.\r\n"
							+ "\r\n" + "");

			notificationService.sendMessage(simpleMailMessage);
		}
    }
}
