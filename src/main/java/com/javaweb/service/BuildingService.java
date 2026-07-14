package com.javaweb.service;

import java.util.List;


import com.javaweb.model.BuildingDetailResponse;
import com.javaweb.model.BuildingRequest;
import com.javaweb.model.BuildingSearchRequest;
import com.javaweb.model.BuildingResponse;

//hàm abtract không có body
//interface chỉ chứa các hằng số và abstract method
//
public interface BuildingService {
	List<BuildingResponse> findAll(BuildingSearchRequest request);

	BuildingDetailResponse findDetailById(Long id);

	void insertOrUpdate(BuildingRequest request);
	void delete(List<Long> ids);
}
