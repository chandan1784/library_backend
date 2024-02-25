package com.studyroom.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studyroom.library.model.entity.LibraryEntity;

public interface LibraryRepository extends JpaRepository<LibraryEntity, Long>{

	List<LibraryEntity> findByDistrictIdAndLocalityIdIn(Long districtId, List<Long> localityIds);

	List<LibraryEntity> findByDistrictId(Long districtId);

}
