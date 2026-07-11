package com.javaweb.repository.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "userName")
    private String userName;
    @Column(name = "passWord")
    private String passWord;
    @Column(name = "status")
    private String status;
    @Column(name = "createDate")
    private Date createDate;
    @Column(name = "modifiedDate")
    private Date modifiedDate;
//    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY )
//    private List<UserRoleEntity> userRoleEntities = new ArrayList<>();


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "userrole",// ten bang trung gian
    joinColumns = @JoinColumn(name = "userId",nullable = false),// join voi khoa ngoai cua bang hien tai
    inverseJoinColumns = @JoinColumn(name = "roleId",nullable = false))
    private List<RoleEntity> roles = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "assignmentbuilding",
    joinColumns = @JoinColumn(name = "userId",nullable = false),
    inverseJoinColumns = @JoinColumn(name = "buildingId",nullable = false))
    private List<BuildingEntity> buildings;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "assignmentcustomer",
    joinColumns = @JoinColumn(name = "userId",nullable = false),
    inverseJoinColumns = @JoinColumn(name = "customerId",nullable = false))
    private List<CustomerEntity> customers;


    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }

//    public List<UserRoleEntity> getUserRoleEntities() {
//        return userRoleEntities;
//    }
//
//    public void setUserRoleEntities(List<UserRoleEntity> userRoleEntities) {
//        this.userRoleEntities = userRoleEntities;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;

    }

    public List<BuildingEntity> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<BuildingEntity> buildings) {
        this.buildings = buildings;
    }

    public List<CustomerEntity> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerEntity> customers) {
        this.customers = customers;
    }
}
