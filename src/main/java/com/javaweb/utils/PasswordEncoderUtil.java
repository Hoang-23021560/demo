package com.javaweb.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Utility: chạy main() để lấy BCrypt hash của mật khẩu,
 * sau đó dùng hash đó để UPDATE vào database.
 *
 * Cách dùng:
 *   1. Run class này như Java Application
 *   2. Copy hash output
 *   3. Chạy SQL: UPDATE user SET passWord = '<hash>' WHERE userName = 'nguyenvana';
 */
public class PasswordEncoderUtil {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "password123";
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("Raw      : " + rawPassword);
        System.out.println("BCrypt   : " + encodedPassword);
        System.out.println();
        System.out.println("-- Chạy các lệnh SQL sau để cập nhật mật khẩu BCrypt:");
        System.out.println("UPDATE user SET passWord = '" + encodedPassword + "' WHERE userName = 'nguyenvana';");
        System.out.println("UPDATE user SET passWord = '" + encodedPassword + "' WHERE userName = 'tranvanb';");
        System.out.println("UPDATE user SET passWord = '" + encodedPassword + "' WHERE userName = 'lethic';");
    }
}
