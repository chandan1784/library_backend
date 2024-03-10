package com.studyroom.user.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Service;

//@FeignClient(url="NOTIFICATION-SERVICE", value="Notification-Client")
@FeignClient(name="NOTIFICATION-SERVICE")
public interface NotificationClient {
	
    @GetMapping("/notification")
    String welcome();

}
