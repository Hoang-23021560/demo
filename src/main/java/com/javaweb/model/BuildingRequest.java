package com.javaweb.model;

import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public class BuildingRequest {


    private Long id;
    private String name;
    private Long floorArea;
    private Long districtId;
    private String ward;
    private String street;
    private Integer numberOfBasement;
    private String direction;
    private String level;


    private Double rentPrice;
    private Double serviceFee;
    private String managerName;
    private String managerPhone;


    private List<String> buildingTypes;

    private List<Long> staffIds;

    private List<Integer> rentAreas;

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

    public Long getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(Long floorArea) {
        this.floorArea = floorArea;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumberOfBasement() {
        return numberOfBasement;
    }

    public void setNumberOfBasement(Integer numberOfBasement) {
        this.numberOfBasement = numberOfBasement;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }


    public Double getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Double rentPrice) {
        this.rentPrice = rentPrice;
    }

    public Double getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(Double serviceFee) {
        this.serviceFee = serviceFee;
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

    public List<String> getBuildingTypes() {
        return buildingTypes;
    }

    public void setBuildingTypes(List<String> buildingTypes) {
        this.buildingTypes = buildingTypes;
    }

    public List<Long> getStaffIds() {
        return staffIds;
    }

    public void setStaffIds(List<Long> staffIds) {
        this.staffIds = staffIds;
    }

    public List<Integer> getRentAreas() {
        return rentAreas;
    }

    public void setRentAreas(List<Integer> rentAreas) {
        this.rentAreas = rentAreas;
    }
}
