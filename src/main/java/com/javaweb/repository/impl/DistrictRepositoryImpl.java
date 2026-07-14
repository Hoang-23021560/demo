package com.javaweb.repository.impl;

import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.entity.DistrictEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DistrictRepositoryImpl implements DistrictRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<DistrictEntity> findAll() {
        return entityManager
                .createQuery("SELECT d FROM DistrictEntity d ORDER BY d.nameDistrict", DistrictEntity.class)
                .getResultList();
    }

    @Override
    public DistrictEntity findById(Long id) {
        return entityManager.find(DistrictEntity.class, id);
    }
}
