package com.studyroom.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.studyroom.library.model.entity.LibraryEntity;

public interface LibraryRepository extends JpaRepository<LibraryEntity, Long>{

	List<LibraryEntity> findByDistrictIdAndLocalityIdIn(Long districtId, List<Long> localityIds);

	List<LibraryEntity> findByDistrictId(Long districtId);

	List<LibraryEntity> findByLocalityId(Long districtId);

	List<LibraryEntity> findByDistrictNameAndLocalityNameContainingIgnoreCase(String districtName, String localityName);

	List<LibraryEntity> findByDistrictNameContainingIgnoreCase(String districtName);

	List<LibraryEntity> findByLocalityNameContainingIgnoreCase(String localityName);

}
