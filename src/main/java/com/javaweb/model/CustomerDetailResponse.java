package com.javaweb.model;

import java.util.List;

/**
 * DTO trả về đầy đủ thông tin một khách hàng — dùng cho modal sửa.
 */
public class CustomerDetailResponse {

    private Long id;
    private String name;
    private String phone;
    private String email;
    private List<Long> staffIds;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<Long> getStaffIds() { return staffIds; }
    public void setStaffIds(List<Long> staffIds) { this.staffIds = staffIds; }
}
