package com.javaweb.config;

import com.javaweb.repository.entity.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * DataInitializer: chạy 1 lần khi app khởi động.
 * Tự động encode password plain-text thành BCrypt nếu chưa được encode.
 * Nhận biết plain-text bằng cách kiểm tra prefix "$2a$" của BCrypt.
 */
@Component
public class DataInitializer implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        TypedQuery<UserEntity> query = entityManager.createQuery(
                "SELECT u FROM UserEntity u", UserEntity.class);
        List<UserEntity> users = query.getResultList();

        int count = 0;
        for (UserEntity user : users) {
            String pwd = user.getPassWord();
            // BCrypt hash luôn bắt đầu bằng $2a$, $2b$ hoặc $2y$
            if (pwd != null && !pwd.startsWith("$2a$") && !pwd.startsWith("$2b$") && !pwd.startsWith("$2y$")) {
                String encoded = passwordEncoder.encode(pwd);
                user.setPassWord(encoded);
                entityManager.merge(user);
                count++;
                log.info("[DataInitializer] Encoded password for user: {}", user.getUserName());
            }
        }

        if (count > 0) {
            log.info("[DataInitializer] Done. Encoded {} plain-text password(s).", count);
        } else {
            log.info("[DataInitializer] All passwords are already BCrypt encoded. Nothing to do.");
        }
    }
}
