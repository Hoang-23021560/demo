package com.javaweb.controllerAdvice;

import com.javaweb.customexception.FieldRequierdException;
import com.javaweb.model.ErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * ControllerAdvisor: xử lý tập trung các loại exception.
 * Áp dụng cho tất cả @RestController (REST API).
 * Các trang web (Thymeleaf) bắt lỗi qua errorPage trong SecurityConfig.
 */
@RestControllerAdvice
public class ControllerAdvisor {

    // ── Lỗi validation field ─────────────────────────────────────
    @ExceptionHandler(FieldRequierdException.class)
    public ResponseEntity<ErrorResponseDTO> handleFieldRequired(
            FieldRequierdException ex, HttpServletRequest request) {

        ErrorResponseDTO error = new ErrorResponseDTO();
        error.setError("FIELD_REQUIRED");
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        error.setDetail(details);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // ── Bean Validation (@Valid) ──────────────────────────────────
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidation(
            MethodArgumentNotValidException ex) {

        ErrorResponseDTO error = new ErrorResponseDTO();
        error.setError("VALIDATION_FAILED");
        List<String> details = new ArrayList<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(fe -> details.add(fe.getField() + ": " + fe.getDefaultMessage()));
        error.setDetail(details);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // ── Không tìm thấy tài nguyên ────────────────────────────────
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleNotFound(NoHandlerFoundException ex) {
        ErrorResponseDTO error = new ErrorResponseDTO();
        error.setError("NOT_FOUND");
        List<String> details = new ArrayList<>();
        details.add("Không tìm thấy endpoint: " + ex.getRequestURL());
        error.setDetail(details);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // ── Không có quyền truy cập (REST) ───────────────────────────
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponseDTO> handleAccessDenied(AccessDeniedException ex) {
        ErrorResponseDTO error = new ErrorResponseDTO();
        error.setError("ACCESS_DENIED");
        List<String> details = new ArrayList<>();
        details.add("Bạn không có quyền thực hiện thao tác này.");
        error.setDetail(details);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    // ── Lỗi chung ────────────────────────────────────────────────
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGeneral(
            Exception ex, HttpServletRequest request) {

        // Chỉ bắt lỗi cho REST API, bỏ qua request từ trình duyệt (web form)
        String acceptHeader = request.getHeader("Accept");
        if (acceptHeader != null && acceptHeader.contains("text/html")) {
            // Ném lại để Spring MVC xử lý (hiển thị trang error)
            throw new RuntimeException(ex);
        }

        ErrorResponseDTO error = new ErrorResponseDTO();
        error.setError("INTERNAL_SERVER_ERROR");
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage() != null ? ex.getMessage() : "Đã xảy ra lỗi không xác định");
        error.setDetail(details);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
