package com.javaweb.repository.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "rentArea")
public class RentAreaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "value")
    private Integer value;
//    @Column(name = "buildingId")
//    private Long buildingId;
    @Column(name = "floor")
    private String floor;
    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "buildingId") // sinh ra cot buildingId
    private BuildingEntity building;

    public BuildingEntity getBuilding() {
        return building;
    }

    public void setBuilding(BuildingEntity building) {
        this.building = building;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

//    public Long getBuildingId() {
//        return buildingId;
//    }
//
//    public void setBuildingId(Long buildingId) {
//        this.buildingId = buildingId;
//    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
