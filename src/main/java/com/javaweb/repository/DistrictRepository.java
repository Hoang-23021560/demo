package com.javaweb.repository;

import com.javaweb.repository.entity.DistrictEntity;

import java.util.List;

public interface DistrictRepository {
    List<DistrictEntity> findAll();
    DistrictEntity findById(Long id);
}
