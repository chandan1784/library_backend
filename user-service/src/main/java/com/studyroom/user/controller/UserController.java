package com.studyroom.user.controller;

import com.studyroom.user.model.dto.UserDTO;
import com.studyroom.user.service.NetworkManager;
import com.studyroom.user.service.NotificationClient;
import com.studyroom.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final NetworkManager networkManager;
	private final NotificationClient notificationClient;

    public UserController(UserService userService,NotificationClient notificationClient, NetworkManager networkManager) {
        this.userService = userService;
        this.notificationClient = notificationClient;
        this.networkManager = networkManager;

    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        if(createdUser!=null)
         return ResponseEntity.ok("User Added Successfully");
		return null;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO user = userService.getUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/phoneNumber/{phoneNumber}")
    public ResponseEntity<UserDTO> getUserByPhoneNumber(@PathVariable String phoneNumber) {
        UserDTO user = userService.getUserByPhoneNumber(phoneNumber);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/welcome")
    public String welcome() {
		//return "Welcome to my library project 1";
    	//return notificationClient.welcome(); //using feignClient api call
    	return networkManager.sendGetRequest();//using HttpUrlConnection api call
    }

}
