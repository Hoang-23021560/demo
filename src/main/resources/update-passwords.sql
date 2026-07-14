-- ============================================================
-- Cập nhật mật khẩu sang BCrypt cho Spring Security
-- Raw password: password123
-- Hash được tạo bởi BCryptPasswordEncoder (strength=10)
-- ============================================================

-- Chạy PasswordEncoderUtil.java để lấy hash mới nhất, sau đó
-- thay thế giá trị bên dưới. Ví dụ hash mẫu:

UPDATE user SET passWord = '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt5Zymq'
WHERE userName = 'nguyenvana';

UPDATE user SET passWord = '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt5Zymq'
WHERE userName = 'tranvanb';

UPDATE user SET passWord = '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt5Zymq'
WHERE userName = 'lethic';

-- Kiểm tra:
SELECT id, userName, LEFT(passWord, 20) as pw_preview, status FROM user;
