package com.studyroom.user.model.mapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.studyroom.user.model.entity.UserEntity;
import com.studyroom.user.model.dto.UserDTO;

@Component
public class UserMapper extends BaseMapper<UserEntity, UserDTO>{
    @Override
    public UserEntity convertToEntity(UserDTO dto, Object... args) {
        UserEntity userEntity = new UserEntity();
        if (dto != null) {
            BeanUtils.copyProperties(dto, userEntity);
        }
        return userEntity;
    }

    @Override
    public UserDTO convertToDto(UserEntity entity, Object... args) {
    	UserDTO user = new UserDTO();
        if (entity != null) {
            BeanUtils.copyProperties(entity, user);
        }
        return user;
    }
}

/*@Component
public class UserMapper {
    public UserEntity convertToEntity(UserDTO dto) {
    	UserEntity userEntity = new UserEntity();
    	userEntity.setUid(dto.getUid());
    	userEntity.setPhoneNumber(dto.getPhoneNumber());
        return userEntity;
    }

    public UserDTO convertToDto(UserEntity entity) {
        UserDTO dto = new UserDTO();
        dto.setUid(entity.getUid());
        dto.setPhoneNumber(entity.getPhoneNumber());
        return dto;
    }
}*/




