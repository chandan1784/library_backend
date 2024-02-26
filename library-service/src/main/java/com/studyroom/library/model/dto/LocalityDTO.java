package com.studyroom.library.model.dto;

import com.studyroom.library.model.entity.DistrictEntity;

public class LocalityDTO {

	private DistrictEntity district;
	private Long id;
	private String name;
	// private Long districtId; 
	private double lat;
	private double lon;

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

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public DistrictEntity getDistrict() {
		return district;
	}

	public void setDistrict(DistrictEntity district) {
		this.district = district;
	}

//	public Long getDistrictId() {
//	return districtId;
//}
//public void setDistrictId(Long districtId) {
//	this.districtId = districtId;
//}

}
