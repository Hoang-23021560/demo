package com.javaweb.service;

import java.util.List;
import java.util.Map;


import com.javaweb.Builder.BuildingSearchBuilder;
import com.javaweb.model.BuildingSearchRequest;
import com.javaweb.model.BuildingSearchResponse;
import com.javaweb.repository.entity.BuildingEntity;

//hàm abtract không có body
//interface chỉ chứa các hằng số và abstract method
//
public interface BuildingService {
	List<BuildingSearchResponse> findAll( BuildingSearchRequest request);
}
