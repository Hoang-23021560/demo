package com.javaweb.controller;

import com.javaweb.model.BuildingResponse;
import com.javaweb.model.BuildingSearchRequest;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.UserEntity;
import com.javaweb.service.BuildingService;
import com.javaweb.service.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * AdminBuildingController: trang quản trị - quản lý tòa nhà.
 * Yêu cầu đăng nhập với ROLE_MANAGER hoặc ROLE_STAFF.
 */
@Controller
@RequestMapping("/admin")
public class AdminBuildingController {

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    /**
     * GET /admin/building - hiển thị trang danh sách tòa nhà với form tìm kiếm
     */
    @GetMapping({"/", "/building"})
    public String buildingList(Model model) {
        // Load data cho dropdown
        List<DistrictEntity> districts = districtRepository.findAll();
        List<UserEntity> staffList = userRepository.findAllStaff();

        model.addAttribute("districts", districts);
        model.addAttribute("staffList", staffList);
        model.addAttribute("searchRequest", new BuildingSearchRequest());
        model.addAttribute("buildings", List.of()); // Chưa tìm kiếm -> rỗng

        addCurrentUserToModel(model);
        return "admin/building-list";
    }

    /**
     * POST /admin/building/search - thực hiện tìm kiếm
     */
    @PostMapping("/building/search")
    public String searchBuilding(
            @ModelAttribute("searchRequest") BuildingSearchRequest request,
            Model model) {

        List<BuildingResponse> buildings = buildingService.findAll(request);

        // Load lại data cho dropdown sau khi search
        List<DistrictEntity> districts = districtRepository.findAll();
        List<UserEntity> staffList = userRepository.findAllStaff();

        model.addAttribute("districts", districts);
        model.addAttribute("staffList", staffList);
        model.addAttribute("searchRequest", request);
        model.addAttribute("buildings", buildings);
        model.addAttribute("totalResults", buildings.size());

        addCurrentUserToModel(model);
        return "admin/building-list";
    }

    /**
     * GET /admin/account - trang quản lý tài khoản (placeholder)
     */
    @GetMapping("/account")
    public String accountManagement(Model model) {
        addCurrentUserToModel(model);
        return "admin/account-list";
    }

    /**
     * GET /admin/customer - trang quản lý khách hàng (placeholder)
     */
    @GetMapping("/customer")
    public String customerManagement(Model model) {
        addCurrentUserToModel(model);
        return "admin/customer-list";
    }

    // -------------------------------------------------------
    // Helper: thêm tên user hiện tại vào model
    // -------------------------------------------------------
    private void addCurrentUserToModel(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            String userName = auth.getName();
            UserEntity userEntity = customUserDetailsService.findUserEntityByUsername(userName);
            model.addAttribute("currentUser", userName);
            model.addAttribute("currentUserEntity", userEntity);
        }
    }
}
