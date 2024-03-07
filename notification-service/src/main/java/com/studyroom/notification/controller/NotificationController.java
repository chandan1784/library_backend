package com.studyroom.notification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.studyroom.notification.dto.EmailTemplate;
import com.studyroom.notification.service.NotificationService;

@RestController
public class NotificationController {
	@Autowired
	private NotificationService notificationService;

	/*
	//Using RequestParam
	@PostMapping("/sendEmail")
	public String sendMessage(@RequestParam String fromEmail, @RequestParam String toEmail,
			@RequestParam String subject, @RequestParam String body) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

		simpleMailMessage.setFrom(fromEmail);

		simpleMailMessage.setTo(toEmail);

		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(body);

		notificationService.sendMessage(simpleMailMessage);

		return "mail is sent successfully";
	}*/
	

	@PostMapping("/sendEmail")
	public String sendMessage(@RequestBody EmailTemplate emailTemplate) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		
		 // Extracting values from the emailTemplate object
        String fromEmail = emailTemplate.getFromEmail();
        String toEmail = emailTemplate.getToEmail();
        String subject = emailTemplate.getSubject();
        String body = emailTemplate.getBody();

		simpleMailMessage.setFrom(fromEmail);
		simpleMailMessage.setTo(toEmail);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(body);

		notificationService.sendMessage(simpleMailMessage);
		//return null;

		return "mail is sent successfully";
	}
}