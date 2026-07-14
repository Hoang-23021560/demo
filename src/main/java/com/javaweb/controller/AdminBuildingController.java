package com.javaweb.controller;

import com.javaweb.model.BuildingResponse;
import com.javaweb.model.BuildingSearchRequest;
import com.javaweb.model.CustomerResponse;
import com.javaweb.model.CustomerSearchRequest;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.UserEntity;
import com.javaweb.service.BuildingService;
import com.javaweb.service.CustomerService;
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

@Controller
@RequestMapping("/admin")
public class AdminBuildingController {

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    // -------------------------------------------------------
    // BUILDING
    // -------------------------------------------------------

    @GetMapping({"/", "/building"})
    public String buildingList(Model model) {
        List<DistrictEntity> districts = districtRepository.findAll();
        List<UserEntity> staffList = userRepository.findAllStaff();
        model.addAttribute("districts", districts);
        model.addAttribute("staffList", staffList);
        model.addAttribute("searchRequest", new BuildingSearchRequest());
        model.addAttribute("buildings", List.of());
        addCurrentUserToModel(model);
        return "admin/building-list";
    }

    @PostMapping("/building/search")
    public String searchBuilding(
            @ModelAttribute("searchRequest") BuildingSearchRequest request,
            Model model) {
        List<BuildingResponse> buildings = buildingService.findAll(request);
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

    // -------------------------------------------------------
    // CUSTOMER
    // -------------------------------------------------------

    @GetMapping("/customer")
    public String customerList(Model model) {
        // Load tất cả KH ngay khi vào trang
        List<CustomerResponse> customers = customerService.findALl(new CustomerSearchRequest());
        List<UserEntity> staffList = userRepository.findAllStaff();
        model.addAttribute("customers", customers);
        model.addAttribute("staffList", staffList);
        model.addAttribute("searchRequest", new CustomerSearchRequest());
        model.addAttribute("totalResults", customers.size());
        addCurrentUserToModel(model);
        return "admin/customer-list";
    }

    @PostMapping("/customer/search")
    public String searchCustomer(
            @ModelAttribute("searchRequest") CustomerSearchRequest request,
            Model model) {
        List<CustomerResponse> customers = customerService.findALl(request);
        List<UserEntity> staffList = userRepository.findAllStaff();
        model.addAttribute("customers", customers);
        model.addAttribute("staffList", staffList);
        model.addAttribute("searchRequest", request);
        model.addAttribute("totalResults", customers.size());
        addCurrentUserToModel(model);
        return "admin/customer-list";
    }

    // -------------------------------------------------------
    // ACCOUNT
    // -------------------------------------------------------

    @GetMapping("/account")
    public String accountManagement(Model model) {
        addCurrentUserToModel(model);
        return "admin/account-list";
    }

    // -------------------------------------------------------
    // Helper
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
