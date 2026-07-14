package com.javaweb.controller;

import com.javaweb.model.BuildingResponse;
import com.javaweb.model.BuildingSearchRequest;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * HomeController: xử lý các trang public (trang chủ, liên hệ, giới thiệu, sản phẩm...)
 */
@Controller
public class HomeController {

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private DistrictRepository districtRepository;

    @GetMapping({"/", "/trang-chu"})
    public String home() {
        return "index";
    }

    // -------------------------------------------------------
    // Giới thiệu — hiển thị tất cả tòa nhà + form tìm kiếm
    // -------------------------------------------------------
    @GetMapping("/gioi-thieu")
    public String gioiThieu(Model model) {
        loadBuildingData(model, new BuildingSearchRequest(), buildingService.findAll(new BuildingSearchRequest()));
        return "gioi-thieu";
    }

    @PostMapping("/gioi-thieu/search")
    public String gioiThieuSearch(
            @ModelAttribute("searchRequest") BuildingSearchRequest request,
            Model model) {
        List<BuildingResponse> buildings = buildingService.findAll(request);
        loadBuildingData(model, request, buildings);
        return "gioi-thieu";
    }

    // -------------------------------------------------------
    // Sản phẩm — hiển thị tất cả tòa nhà + form tìm kiếm
    // -------------------------------------------------------
    @GetMapping("/san-pham")
    public String sanPham(Model model) {
        loadBuildingData(model, new BuildingSearchRequest(), buildingService.findAll(new BuildingSearchRequest()));
        return "san-pham";
    }

    @PostMapping("/san-pham/search")
    public String sanPhamSearch(
            @ModelAttribute("searchRequest") BuildingSearchRequest request,
            Model model) {
        List<BuildingResponse> buildings = buildingService.findAll(request);
        loadBuildingData(model, request, buildings);
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

    // -------------------------------------------------------
    // Helper dùng chung cho cả 2 trang
    // -------------------------------------------------------
    private void loadBuildingData(Model model, BuildingSearchRequest request, List<BuildingResponse> buildings) {
        List<DistrictEntity> districts = districtRepository.findAll();
        model.addAttribute("districts", districts);
        model.addAttribute("searchRequest", request);
        model.addAttribute("buildings", buildings);
        model.addAttribute("totalResults", buildings.size());
    }
}
