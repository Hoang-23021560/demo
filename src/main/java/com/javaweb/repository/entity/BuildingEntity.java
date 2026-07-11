package com.javaweb.repository.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//Nhiệm vụ cốt lõi của BuildingEntity là đại diện trực tiếp cho cấu trúc dữ liệu của bảng building trong Database dưới dạng một đối tượng Java (Object).
//Nó hoạt động như một chiếc "khuôn đúc" để chứa dữ liệu thô móc từ MySQL lên, trước khi đưa đi xử lý logic.
// java bean
// BuildingEntity thường nên chứa toàn bộ các cột của bảng Building,
// vì nhiệm vụ của Entity là ánh xạ (mapping) giữa Java và bảng trong database.
@Entity
@Table(name = "building")
public class BuildingEntity {
	@Id//khoa chinh
	@GeneratedValue(strategy = GenerationType.IDENTITY )// tu dong tang dan
	private Long id;
	@Column(name = "name")
    private String name;
//	@Column(name = "districtId")
//    private Long districtId;
	@Column(name = "ward")
    private String ward;
	@Column(name = "street")
    private String street;
	@Column(name = "numberOfBasement")
    private Integer numberOfBasement;
	@Column(name = "floorArea")
    private Double floorArea;
	@Column(name = "direction")
    private String direction;
	@Column(name = "level")
    private String level;
	@Column(name = "rentPrice")
    private Double rentPrice;
	@Column(name = "serviceFee")
    private Double serviceFee;
	@Column(name = "managerName")
    private String managerName;
	@Column(name = "managerPhone")
    private String managerPhone;
	@Column(name = "brokerageFeePercent")
    private Double brokerageFeePercent;
	@Column(name = "structure")

	private String structure;
	@Column(name = "priceDescription")
	private String priceDescription;
	@Column(name = "carFee")
	private Double carFee;
	@Column(name = "motoFee")
	private Double motoFee;
	@Column(name = "overtimeFee")
	private Double overtimeFee;

	@Column(name = "electricityFee")
	private Double electricityFee;

	@Column(name = "depositMonths")
	private Integer depositMonths;

	@Column(name = "paymentTerm", length = 100)
	private String paymentTerm;
	@Column(name = "note", columnDefinition = "TEXT")
	private String note;

	@Column(name = "createdDate")
	private Date createdDate;

	@Column(name = "modifiedDate")
	private Date modifiedDate;

	public String getStructure() {
		return structure;
	}

	public void setStructure(String structure) {
		this.structure = structure;
	}

	public String getPriceDescription() {
		return priceDescription;
	}

	public void setPriceDescription(String priceDescription) {
		this.priceDescription = priceDescription;
	}

	public Double getCarFee() {
		return carFee;
	}

	public void setCarFee(Double carFee) {
		this.carFee = carFee;
	}

	public Double getMotoFee() {
		return motoFee;
	}

	public void setMotoFee(Double motoFee) {
		this.motoFee = motoFee;
	}

	public Double getOvertimeFee() {
		return overtimeFee;
	}

	public void setOvertimeFee(Double overtimeFee) {
		this.overtimeFee = overtimeFee;
	}

	public Double getElectricityFee() {
		return electricityFee;
	}

	public void setElectricityFee(Double electricityFee) {
		this.electricityFee = electricityFee;
	}

	public Integer getDepositMonths() {
		return depositMonths;
	}

	public void setDepositMonths(Integer depositMonths) {
		this.depositMonths = depositMonths;
	}

	public String getPaymentTerm() {
		return paymentTerm;
	}

	public void setPaymentTerm(String paymentTerm) {
		this.paymentTerm = paymentTerm;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public List<BuildingTypeEntity> getBuildingType() {
		return buildingType;
	}

	public void setBuildingType(List<BuildingTypeEntity> buildingType) {
		this.buildingType = buildingType;
	}

	public List<UserEntity> getUser() {
		return user;
	}

	public void setUser(List<UserEntity> user) {
		this.user = user;
	}
//	@Column(name = "decorationTime", length = 100)
//	private String decorationTime;


    
//    // --- CÁC TRƯỜNG BỔ SUNG ĐỂ HỨNG DỮ LIỆU JOIN ---
//    private String nameDistrict; // Lấy từ bảng District
//    private String rentAreas;    // Xâu diện tích lấy từ bảng RentArea

	public DistrictEntity getDistrict() {
		return district;
	}

	public void setDistrict(DistrictEntity district) {
		this.district = district;
	}

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "districtId")//join khoa ngoai //sinh ra cot districtId
	private DistrictEntity district; //gionng cai mappedBy that ra mappedBy phải giống tên biến
    // Thêm các Constructor, Getter và Setter cho tất cả các trường...
	@OneToMany(mappedBy = "building",fetch = FetchType.LAZY)
	private List<RentAreaEntity> rentarea = new ArrayList<>();


	@OneToMany(mappedBy = "building",fetch = FetchType.LAZY)
	private List<BuildingTypeEntity> buildingType;

	@ManyToMany(mappedBy = "buildings",fetch = FetchType.LAZY)
	private List<UserEntity> user = new ArrayList<>();

	public List<RentAreaEntity> getRentarea() {
		return rentarea;
	}

	public void setRentarea(List<RentAreaEntity> rentarea) {
		this.rentarea = rentarea;
	}
//
//	public String getNameDistrict() { return nameDistrict; }
//    public void setNameDistrict(String nameDistrict) { this.nameDistrict = nameDistrict; }
//
//    public String getRentAreas() { return rentAreas; }
//    public void setRentAreas(String rentAreas) { this.rentAreas = rentAreas; }
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
//	public Long getDistrictId() {
//		return districtId;
//	}
//	public void setDistrictId(Long districtId) {
//		this.districtId = districtId;
//	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}
	public void setNumberOfBasement(Integer numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}
	public Double getFloorArea() {
		return floorArea;
	}
	public void setFloorArea(Double floorArea) {
		this.floorArea = floorArea;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public Double getRentPrice() {
		return rentPrice;
	}
	public void setRentPrice(Double rentPrice) {
		this.rentPrice = rentPrice;
	}
	public Double getServiceFee() {
		return serviceFee;
	}
	public void setServiceFee(Double serviceFee) {
		this.serviceFee = serviceFee;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerPhone() {
		return managerPhone;
	}
	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}
	public Double getBrokerageFeePercent() {
		return brokerageFeePercent;
	}
	public void setBrokerageFeePercent(Double brokerageFeePercent) {
		this.brokerageFeePercent = brokerageFeePercent;
	}
    
    
	
}
