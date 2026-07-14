package com.javaweb.service.impl;

import com.javaweb.Builder.BuildingSearchBuilder;
import com.javaweb.Converter.BuildingSearchBuilderConverter;
import com.javaweb.model.BuildingDetailResponse;
import com.javaweb.model.BuildingRequest;
import com.javaweb.model.BuildingResponse;
import com.javaweb.model.BuildingSearchRequest;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.*;
import com.javaweb.service.BuildingService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BuildingSearchBuilderConverter buildingSearchBuilderConverter;

    @Override
    public List<BuildingResponse> findAll(BuildingSearchRequest request) {
//        BuildingSearchBuilder buildingSearchBuilder = buildingSearchBuilderConverter.toBuildingSearchConverter(params,Code);

        BuildingSearchBuilder searchBuilder = new BuildingSearchBuilder.Builder()
                .setName(request.getName())
                .setFloorArea(request.getFloorArea() != null ? request.getFloorArea().doubleValue() : null)
                .setDistrictId(request.getDistrictId())
                .setWard(request.getWard())
                .setStreet(request.getStreet())
                .setNumberOfBasement(request.getNumberOfBasement())
                .setDirection(request.getDirection())
                .setLevel(request.getLevel())
                .setRentAreaFrom(request.getRentAreaFrom() != null ? request.getRentAreaFrom().doubleValue() : null)
                .setRentAreaTo(request.getRentAreaTo() != null ? request.getRentAreaTo().doubleValue() : null)
                .setRentPriceFrom(request.getRentPriceFrom())
                .setRentPriceTo(request.getRentPriceTo())
                .setManagerName(request.getManagerName())
                .setManagerPhone(request.getManagerPhone())
                .setStaffId(request.getStaffId())
                .setCode(request.getCode())
                .build();
        // 1. Gọi tầng Repository để lấy dữ liệu thực thể đã qua bộ lọc 16 fields
        List<BuildingEntity> buildingEntities = buildingRepository.findAll(searchBuilder);


        // List chứa kết quả trả ra cho người dùng (11 fields)
        List<BuildingResponse> resultList = new ArrayList<>();

        // 2. Vòng lặp duyệt qua từng thực thể và chuyển đổi (Mapping) sang DTO kết quả
        for (BuildingEntity entity : buildingEntities) {
            BuildingResponse response = modelMapper.map(entity, BuildingResponse.class);
            response.setId(entity.getId()); // đảm bảo id luôn được set

            // Field 1: Tên tòa nhà 
            //response.setName(entity.getName());

            // Field 2: Xử lý logic địa chỉ: Số nhà/Đường + Phường + Quận 
            String fullAddress = "";
            if (entity.getStreet() != null && !entity.getStreet().isEmpty()) {
                fullAddress += entity.getStreet();
            }
            if (entity.getWard() != null && !entity.getWard().isEmpty()) {
                fullAddress += (fullAddress.isEmpty() ? "" : ", ") + entity.getWard();
            }
            if (entity.getDistrict().getNameDistrict() != null && !entity.getDistrict().getNameDistrict().isEmpty()) {
                fullAddress += (fullAddress.isEmpty() ? "" : ", ") + entity.getDistrict().getNameDistrict();
            }
            response.setAddress(fullAddress);

            // Field 3: Số tầng hầm 
            //           response.setNumberOfBasement(entity.getNumberOfBasement());
//
//            // Field 4: Tên quản lý 
//            response.setManagerName(entity.getManagerName());
//
//            // Field 5: Số điện thoại quản lý 
//            response.setManagerPhone(entity.getManagerPhone());
//
//            // Field 6: Diện tích sàn 
//            response.setFloorArea(entity.getFloorArea());

            // Field 7: Diện tích trống (Tính toán logic nghiệp vụ nếu có, tạm thời để trống hoặc mặc định) 
            response.setEmptyArea("Đang cập nhật");

            // Field 8: Giá thuê 
            //response.setRentPrice(entity.getRentPrice());

            // Field 9: Diện tích thuê - Nhận trực tiếp cái xâu đã được gom từ GROUP_CONCAT trong DB 
            String rentAreas = entity.getRentarea()
                    .stream()
                    .map(r -> String.valueOf(r.getValue()))
                    .collect(Collectors.joining(", "));

            response.setRentAreas(rentAreas);
            // Field 10: Phí dịch vụ 
            //response.setServiceFee(entity.getServiceFee());

            // Field 11: Phí môi giới (Tính toán: Giá thuê * % phí môi giới nếu có) 
            if (entity.getRentPrice() != null && entity.getBrokerageFeePercent() != null) {
                double calculatedFee = entity.getRentPrice() * (entity.getBrokerageFeePercent() / 100);
                response.setBrokerageFee(calculatedFee);
            } else {
                response.setBrokerageFee(0.0);
            }

            // Thêm đối tượng DTO hoàn chỉnh vào danh sách trả về
            resultList.add(response);
        }

        return resultList;
    }

    @Override
    @Transactional
    public BuildingDetailResponse findDetailById(Long id) {
        BuildingEntity entity = buildingRepository.findById(id);
        if (entity == null) {
            throw new RuntimeException("Toa nha khong ton tai");
        }
        BuildingDetailResponse dto = new BuildingDetailResponse();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setWard(entity.getWard());
        dto.setStreet(entity.getStreet());
        dto.setDirection(entity.getDirection());
        dto.setLevel(entity.getLevel());
        dto.setNumberOfBasement(entity.getNumberOfBasement());
        dto.setFloorArea(entity.getFloorArea() != null ? entity.getFloorArea().longValue() : null);
        dto.setRentPrice(entity.getRentPrice());
        dto.setServiceFee(entity.getServiceFee());
        dto.setManagerName(entity.getManagerName());
        dto.setManagerPhone(entity.getManagerPhone());

        if (entity.getDistrict() != null) {
            dto.setDistrictId(entity.getDistrict().getId());
        }

        List<Integer> rentAreaList = entity.getRentarea().stream()
                .map(RentAreaEntity::getValue)
                .collect(Collectors.toList());
        dto.setRentAreas(rentAreaList);

        List<String> buildingTypeList = entity.getBuildingType().stream()
                .map(BuildingTypeEntity::getCode)
                .collect(Collectors.toList());
        dto.setBuildingTypes(buildingTypeList);

        List<Long> staffIds = entity.getUser().stream()
                .map(UserEntity::getId)
                .collect(Collectors.toList());
        dto.setStaffIds(staffIds);

        return dto;
    }

    @Override
    @Transactional
    public void insertOrUpdate(BuildingRequest request) {
        BuildingEntity building;
        if (request.getId() != null) {
            building = buildingRepository.findById(request.getId());
            if (building == null) {
                throw new RuntimeException("Toa nha khong ton tai");
            }
        } else {
            building = new BuildingEntity();
        }

        // Map các field đơn giản trước (name, ward, street, direction, level,
        // numberOfBasement, rentPrice, serviceFee, managerName, managerPhone...)
        // Dùng map thủ công để tránh ModelMapper ghi đè district/rentarea/buildingType
        building.setName(request.getName());
        building.setWard(request.getWard());
        building.setStreet(request.getStreet());
        building.setDirection(request.getDirection());
        building.setLevel(request.getLevel());
        building.setNumberOfBasement(request.getNumberOfBasement());
        building.setRentPrice(request.getRentPrice());
        building.setServiceFee(request.getServiceFee());
        building.setManagerName(request.getManagerName());
        building.setManagerPhone(request.getManagerPhone());
        if (request.getFloorArea() != null) {
            building.setFloorArea(request.getFloorArea().doubleValue());
        }

        // Set timestamp
        Date now = new Date();
        if (request.getId() == null) {
            building.setCreatedDate(now);  // chỉ set khi tạo mới
        }
        building.setModifiedDate(now);

        // Xử lý District
        if (request.getDistrictId() != null) {
            DistrictEntity district = buildingRepository.findByDistrictId(request.getDistrictId());
            building.setDistrict(district);
        }

        // Xử lý BuildingType (xóa cũ, thêm mới vào list gốc Hibernate đang track)
        building.getBuildingType().clear();
        if (request.getBuildingTypes() != null && !request.getBuildingTypes().isEmpty()) {
            for (String code : request.getBuildingTypes()) {
                BuildingTypeEntity buildingType = new BuildingTypeEntity();
                buildingType.setCode(code);
                buildingType.setBuilding(building);
                building.getBuildingType().add(buildingType);
            }
        }

        // Xử lý RentArea (xóa cũ, thêm mới vào list gốc Hibernate đang track)
        building.getRentarea().clear();
        if (request.getRentAreas() != null && !request.getRentAreas().isEmpty()) {
            for (Integer value : request.getRentAreas()) {
                RentAreaEntity rentArea = new RentAreaEntity();
                rentArea.setValue(value);
                rentArea.setBuilding(building);
                building.getRentarea().add(rentArea);
            }
        }

        // Xử lý Staff (xóa quan hệ cũ, gán mới vào list gốc)
        if (request.getId() != null) {
            for (UserEntity user : new ArrayList<>(building.getUser())) {
                user.getBuildings().remove(building);
            }
            building.getUser().clear();
        }

        if (request.getId() != null) {
            buildingRepository.update(building);
        } else {
            buildingRepository.insert(building); // persist trước để có ID
        }

        // Gán staff SAU khi building đã có ID (quan trọng với insert mới)
        if (request.getStaffIds() != null && !request.getStaffIds().isEmpty()) {
            List<UserEntity> users = buildingRepository.findByIdStaff(request.getStaffIds());
            for (UserEntity user : users) {
                if (!user.getBuildings().contains(building)) {
                    user.getBuildings().add(building);
                }
                if (!building.getUser().contains(user)) {
                    building.getUser().add(user);
                }
            }
        }
    }


    @Override
    @Transactional
    public void delete(List<Long> ids) {
        // Thực hiện vòng lặp xóa từng id trong danh sách gửi lên
        for (Long id : ids) {
            buildingRepository.delete(id);
        }
    }

}
