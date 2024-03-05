package com.studyroom.notification.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;

@Service
public class NotificationService {

	@Autowired
	private AmazonSimpleEmailService amazonSimpleEmailService;

	public void sendMessage(SimpleMailMessage simpleMailMessage) {

		Destination destination = new Destination();
		destination.setToAddresses(Arrays.asList(simpleMailMessage.getTo()));

		Content content = new Content();
		content.setData(simpleMailMessage.getText());

		Body body = new Body();
		body.setText(content);

		Content subject = new Content();
		subject.setData(simpleMailMessage.getSubject());

		Message msg = new Message();
		msg.setBody(body);
		msg.setSubject(subject);

		SendEmailRequest emailRequest = new SendEmailRequest();
		emailRequest.setDestination(destination);
		emailRequest.setMessage(msg);
		emailRequest.setSource(simpleMailMessage.getFrom());

		amazonSimpleEmailService.sendEmail(emailRequest);

	}

}
