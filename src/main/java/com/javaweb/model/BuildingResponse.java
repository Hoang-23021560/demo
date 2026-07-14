package com.javaweb.model; // Thay đổi package phù hợp với project của bạn

// BuidlingSearchResponse == BuildingDTO
public class BuildingResponse {

    private Long id;                 // ID tòa nhà — dùng cho edit/delete
    // 11 fields đầu ra theo yêu cầu từ file timkiemtoanha.txt
    private String name;             // Tên tòa nhà
    private String address;          // Địa chỉ đầy đủ: Đường + Phường + Quận
    private Integer numberOfBasement;// Số tầng hầm
    private String managerName;      // Tên quản lý
    private String managerPhone;     // Số điện thoại quản lý
    private Double floorArea;        // Diện tích sàn
    private String emptyArea;        // Diện tích trống (Tính toán từ RentArea AVAILABLE)
    private Double rentPrice;        // Giá thuê
    private String rentAreas;        // Diện tích thuê: Trả về một chuỗi/xâu (VD: "100, 200, 300")
    private Double serviceFee;       // Phí dịch vụ
    private Double brokerageFee;     // Phí môi giới (Có thể tính toán dựa trên % phí môi giới)

    // ==========================================
    // 1. CONSTRUCTORS (Khởi tạo)
    // ==========================================
    
    public BuildingResponse() {
    }

    public BuildingResponse(String name, String address, Integer numberOfBasement, String managerName,
                            String managerPhone, Double floorArea, String emptyArea, Double rentPrice,
                            String rentAreas, Double serviceFee, Double brokerageFee) {
        this.name = name;
        this.address = address;
        this.numberOfBasement = numberOfBasement;
        this.managerName = managerName;
        this.managerPhone = managerPhone;
        this.floorArea = floorArea;
        this.emptyArea = emptyArea;
        this.rentPrice = rentPrice;
        this.rentAreas = rentAreas;
        this.serviceFee = serviceFee;
        this.brokerageFee = brokerageFee;
    }

    // ==========================================
    // 2. GETTERS AND SETTERS
    // ==========================================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getNumberOfBasement() {
        return numberOfBasement;
    }

    public void setNumberOfBasement(Integer numberOfBasement) {
        this.numberOfBasement = numberOfBasement;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public Double getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(Double floorArea) {
        this.floorArea = floorArea;
    }

    public String getEmptyArea() {
        return emptyArea;
    }

    public void setEmptyArea(String emptyArea) {
        this.emptyArea = emptyArea;
    }

    public Double getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Double rentPrice) {
        this.rentPrice = rentPrice;
    }

    public String getRentAreas() {
        return rentAreas;
    }

    public void setRentAreas(String rentAreas) {
        this.rentAreas = rentAreas;
    }

    public Double getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(Double serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Double getBrokerageFee() {
        return brokerageFee;
    }

    public void setBrokerageFee(Double brokerageFee) {
        this.brokerageFee = brokerageFee;
    }
}