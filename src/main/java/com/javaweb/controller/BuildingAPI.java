package com.javaweb.controller;

import java.util.*;

import com.javaweb.model.BuildingDetailResponse;
import com.javaweb.model.BuildingRequest;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.web.bind.annotation.*;

import com.javaweb.model.BuildingSearchRequest;
import com.javaweb.model.BuildingResponse;
import com.javaweb.service.BuildingService;

@RestController
@PropertySource("classpath:application.properties")
public class BuildingAPI {
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private BuildingService buildingService;
	@Value("${dev.nguyen}")
	private String data;
    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;

	@GetMapping("/api/building/")
	public List<BuildingResponse> getBuilding(BuildingSearchRequest request) {
		List<BuildingResponse> result = buildingService.findAll(request);
		return result;
	}

	@GetMapping("/api/building/{id}")
	public BuildingDetailResponse getBuildingDetail(@PathVariable Long id) {
		return buildingService.findDetailById(id);
	}


//	static final String DB_URL = "jdbc:mysql://localhost:3306/databasebuilding1";
//	static final String USER = "root";
//	static final String PASS = "123456";
//	@RequestMapping(value = "/api/building/", method = RequestMethod.GET)
//	@ResponseBody
//	public List<BuildingDTO> getBuilding() {
//		String sql = "SELECT * FROM databasebuilding1.Building";
//		List<BuildingDTO> result = new ArrayList<>();
//		try(Connection conn = DriverManager.getConnection(DB_URL, USER,PASS);
//				Statement stmt = conn.createStatement();
//				ResultSet rs = stmt.executeQuery(sql);
//				){
//			while(rs.next()) {
//				BuildingDTO building = new BuildingDTO();
//				building.setName(rs.getString("name"));
//				building.setStreet(rs.getString("street"));
//				building.setWard(rs.getString("ward"));
//				building.setNumberOfBasement(rs.getInt("numberOfBasement"));
//				result.add(building);
//			}
//			
//		} catch(SQLException e) {
//			e.printStackTrace();
//			System.out.println("Connection database failed ..");
//		}
//		return result;
//		
//	public Object getBuilding(@RequestParam(value = "name") String name,
//			@RequestParam(value = "numberOfBasement") Integer numberOfBasement,
//			@RequestParam(value = "ward") String ward) {
//		// xu ly duoi DB xong roi
//		try {
//			System.out.println(5/0);
//		}
//		catch(Exception e) {
//			ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
//			errorResponseDTO.setError(e.getMessage());
//			List<String> details = new ArrayList<>();
//			details.add("So nguyen lam sao chia het cho 0 duoc");
//			errorResponseDTO.setDetail(details);
//			return errorResponseDTO;
//			
//		}
//		
//		List<BuildingDTO> listBuildings = new ArrayList<>();
//		BuildingDTO buildingDTO1 = new BuildingDTO();
//		buildingDTO1.setName("ABC Building");
//		buildingDTO1.setNumberOfBasement(3);
//		buildingDTO1.setWard("Tan Mai");
//		BuildingDTO buildingDTO2 = new BuildingDTO();
//		buildingDTO2.setName("ACM Building");
//		buildingDTO2.setNumberOfBasement(4);
//		buildingDTO2.setWard("Da Cao");
//		listBuildings.add(buildingDTO1);
//		listBuildings.add(buildingDTO2);
//		return listBuildings;
////		
//		System.out.println(5/0);
//		System.out.println("hello");
//		return null;

	// hàm valiDate để ném ra loại lỗi tên là gì
//	public void valiDate(BuildingDTO buildingDTO) {
//		if(buildingDTO.getName() == null || buildingDTO.getName().equals("") || buildingDTO.getNumberOfBasement() == null) {
//			throw new FieldRequierdException("name or numberOfbasement is null");
//		}
//	}
//
	@PostMapping(value = "/api/building/")
	@Transactional
	public void createBuilding(@RequestBody BuildingRequest request){
		buildingService.insertOrUpdate(request);
}
	@PutMapping(value = "/api/building/")
	@Transactional
	public void updateBuilding(@RequestBody BuildingRequest request){
		buildingService.insertOrUpdate(request);

	}
	@DeleteMapping("/api/building/{ids}")
	@Transactional
	public void deleteBuilding(@PathVariable List<Long> ids) {
		buildingService.delete(ids);
	}

}


