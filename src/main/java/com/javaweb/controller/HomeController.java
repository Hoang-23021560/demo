package com.javaweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * HomeController: xử lý các trang public (trang chủ, liên hệ, giới thiệu...)
 */
@Controller
public class HomeController {

    @GetMapping({"/", "/trang-chu"})
    public String home() {
        return "index";
    }

    @GetMapping("/gioi-thieu")
    public String gioiThieu() {
        return "gioi-thieu";
    }

    @GetMapping("/san-pham")
    public String sanPham() {
        return "san-pham";
    }

    @GetMapping("/tin-tuc")
    public String tinTuc() {
        return "tin-tuc";
    }

    @GetMapping("/lien-he")
    public String lienHe() {
        return "lien-he";
    }

    @GetMapping("/dang-ky")
    public String dangKy() {
        return "dang-ky";
    }
}
