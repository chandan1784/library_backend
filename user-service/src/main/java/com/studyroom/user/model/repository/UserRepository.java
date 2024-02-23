package com.studyroom.user.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studyroom.user.model.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}

