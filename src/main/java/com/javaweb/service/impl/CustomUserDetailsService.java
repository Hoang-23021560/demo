package com.javaweb.service.impl;

import com.javaweb.repository.entity.RoleEntity;
import com.javaweb.repository.entity.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * CustomUserDetailsService: Spring Security sử dụng class này để load user từ database
 * khi người dùng đăng nhập. Nó tra cứu UserEntity theo userName, sau đó chuyển đổi
 * roles thành GrantedAuthority với prefix ROLE_.
 */
@Service
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        // Tìm user theo userName trong database
        TypedQuery<UserEntity> query = entityManager.createQuery(
                "SELECT u FROM UserEntity u WHERE u.userName = :userName", UserEntity.class);
        query.setParameter("userName", userName);

        List<UserEntity> results = query.getResultList();

        if (results.isEmpty()) {
            throw new UsernameNotFoundException("Không tìm thấy tài khoản: " + userName);
        }

        UserEntity userEntity = results.get(0);

        // Kiểm tra trạng thái tài khoản
        boolean isActive = "ACTIVE".equalsIgnoreCase(userEntity.getStatus());

        // Chuyển đổi roles thành GrantedAuthority
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (RoleEntity role : userEntity.getRoles()) {
            // Spring Security yêu cầu prefix ROLE_
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getCode()));
        }

        return User.builder()
                .username(userEntity.getUserName())
                .password(userEntity.getPassWord())
                .authorities(authorities)
                .accountExpired(false)
                .accountLocked(!isActive)
                .credentialsExpired(false)
                .disabled(!isActive)
                .build();
    }

    /**
     * Lấy đầy đủ thông tin UserEntity theo userName (dùng trong controller/service)
     */
    public UserEntity findUserEntityByUsername(String userName) {
        TypedQuery<UserEntity> query = entityManager.createQuery(
                "SELECT u FROM UserEntity u WHERE u.userName = :userName", UserEntity.class);
        query.setParameter("userName", userName);
        List<UserEntity> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }
}
