package com.javaweb.repository;

import com.javaweb.repository.entity.UserEntity;

import java.util.List;

public interface UserRepository {
    List<UserEntity> findAllStaff();
    UserEntity findByUserName(String userName);
    UserEntity findById(Long id);
}
