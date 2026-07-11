package com.javaweb.repository.entity;

import jakarta.persistence.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "district")
public class DistrictEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @Column(name = "nameDistrict")
    private String nameDistrict;
    @Column(name = "code")
    private String code;



    @OneToMany(mappedBy = "district",fetch = FetchType.LAZY)
    private List<BuildingEntity>  buildings = new ArrayList<>();

    public List<BuildingEntity> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<BuildingEntity> Buildings) {
        this.buildings = Buildings;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameDistrict() {
        return nameDistrict;
    }

    public void setNameDistrict(String nameDistrict) {
        this.nameDistrict = nameDistrict;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
