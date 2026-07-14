package com.javaweb.repository.impl;

import com.javaweb.repository.UserRepository;
import com.javaweb.repository.entity.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<UserEntity> findAllStaff() {
        // Lấy tất cả user có role STAFF hoặc MANAGER để hiển thị dropdown nhân viên phụ trách
        return entityManager.createQuery(
                "SELECT DISTINCT u FROM UserEntity u JOIN u.roles r WHERE r.code IN ('STAFF','MANAGER') ORDER BY u.userName",
                UserEntity.class
        ).getResultList();
    }

    @Override
    public UserEntity findByUserName(String userName) {
        List<UserEntity> result = entityManager.createQuery(
                "SELECT u FROM UserEntity u WHERE u.userName = :userName", UserEntity.class)
                .setParameter("userName", userName)
                .getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public UserEntity findById(Long id) {
        return entityManager.find(UserEntity.class, id);
    }
}
