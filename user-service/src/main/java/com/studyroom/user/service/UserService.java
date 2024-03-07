package com.studyroom.user.service;

import com.studyroom.user.message.PublishMessage;
import com.studyroom.user.model.dto.UserDTO;
import com.studyroom.user.model.entity.UserEntity;
import com.studyroom.user.model.mapper.UserMapper;
import com.studyroom.user.model.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PublishMessage publishMessage;
    boolean testIndRestApi=false;
    boolean testIndMessagingBroker=false;
    
    public UserService(UserRepository userRepository, UserMapper userMapper, PublishMessage publishMessage) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.publishMessage=publishMessage;
    }

	public UserDTO createUser(UserDTO userDTO) {
		UserEntity userEntity = userMapper.convertToEntity(userDTO);
		userEntity = userRepository.save(userEntity);
		UserDTO createdUser = userMapper.convertToDto(userEntity);
		
		// Testing communication between Two microservices using Message Broker to send the email after successfull registration via AWS ses email
		if (createdUser != null && testIndMessagingBroker)
			publishMessage.publish("User Added Successfully");

		// Testing communication between Two microservices using HTTP protocol  to send the email after successfull registration via AWS ses email
		if (createdUser != null && testIndRestApi)
			NetworkManager.sendResponse("User Added Successfully");

		return createdUser;
	}

   public List<UserDTO> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userMapper.convertToDtoList(userEntities);
    }

    public UserDTO getUserById(Long id) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        return userEntityOptional.map(userMapper::convertToDto).orElse(null);
    }
    
    public UserDTO getUserByPhoneNumber(String phoneNumber) {
        Optional<UserEntity> userEntityOptional = userRepository.findByPhoneNumber(phoneNumber);
        return userEntityOptional.map(userMapper::convertToDto).orElse(null);
    }
}
