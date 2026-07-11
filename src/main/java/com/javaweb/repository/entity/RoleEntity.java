package com.javaweb.repository.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "role")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "createDate")
    private Date createDate;
    @Column(name = "modifiedDate")
    private Date modifiedDate;
//    @OneToMany(mappedBy = "role",fetch = FetchType.LAZY)
//    private List<UserRoleEntity> userRoleEntities = new ArrayList<>();

    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
    private List<UserEntity> users = new ArrayList<>();

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }
}
