package com.studyroom.library.model.dto;

import com.studyroom.library.model.entity.DistrictEntity;
import com.studyroom.library.model.entity.LocalityEntity;

public class LibraryDTO {

	private Long id;

	private String name;
	private double lat;
	private double lon;
	private DistrictEntity district;
	private LocalityEntity locality;

	public DistrictEntity getDistrict() {
		return district;
	}

	public void setDistrict(DistrictEntity district) {
		this.district = district;
	}

	public LocalityEntity getLocality() {
		return locality;
	}

	public void setLocality(LocalityEntity locality) {
		this.locality = locality;
	}

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
}
