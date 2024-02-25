package com.studyroom.user.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studyroom.user.model.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	Optional<UserEntity> findByPhoneNumber(String phoneNumber);
}

