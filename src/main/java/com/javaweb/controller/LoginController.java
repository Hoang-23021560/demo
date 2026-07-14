package com.javaweb.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * LoginController: hiển thị form đăng nhập.
 * Việc xử lý POST /login do Spring Security tự đảm nhận.
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            Model model) {

        // Nếu đã đăng nhập rồi, chuyển thẳng vào admin
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()
                && !auth.getPrincipal().equals("anonymousUser")) {
            return "redirect:/admin/building";
        }

        if (error != null) {
            model.addAttribute("errorMessage",
                    "Tên đăng nhập hoặc mật khẩu không đúng. Vui lòng thử lại!");
        }
        if (logout != null) {
            model.addAttribute("logoutMessage", "Bạn đã đăng xuất thành công.");
        }

        return "login";
    }
}
