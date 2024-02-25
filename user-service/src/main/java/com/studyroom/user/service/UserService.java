package com.studyroom.user.service;

import com.studyroom.user.model.dto.UserDTO;
import com.studyroom.user.model.entity.UserEntity;
import com.studyroom.user.model.mapper.UserMapper;
import com.studyroom.user.model.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDTO createUser(UserDTO userDTO) {
        UserEntity userEntity = userMapper.convertToEntity(userDTO);
        userEntity = userRepository.save(userEntity);
        return userMapper.convertToDto(userEntity);
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
