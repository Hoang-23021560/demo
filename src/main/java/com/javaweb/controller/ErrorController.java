package com.javaweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ErrorController: xử lý các trang lỗi tùy chỉnh
 */
@Controller
@RequestMapping("/error")
public class ErrorController {

    @GetMapping("/403")
    public String forbidden() {
        return "error/403";
    }

    @GetMapping("/404")
    public String notFound() {
        return "error/404";
    }

    @GetMapping("/500")
    public String internalError() {
        return "error/500";
    }
}
