package com.studyroom.notification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.studyroom.notification.service.NotificationService;

@RestController
public class NotificationController {
	@Autowired
	private NotificationService notificationService;

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
	}
}