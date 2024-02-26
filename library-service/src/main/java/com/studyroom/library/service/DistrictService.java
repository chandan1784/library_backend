package com.studyroom.library.service;

import org.springframework.stereotype.Service;

import com.studyroom.library.model.dto.DistrictDTO;
import com.studyroom.library.model.entity.DistrictEntity;
import com.studyroom.library.model.mapper.DistrictMapper;
import com.studyroom.library.repository.DistrictRepository;

@Service
public class DistrictService {

    private final DistrictRepository districtRepository;
    private final DistrictMapper districtMapper;

    public DistrictService(DistrictRepository districtRepository, DistrictMapper districtMapper) {
        this.districtRepository = districtRepository;
        this.districtMapper = districtMapper;
    }

    public void addDistrict(DistrictDTO districtDTO) {
        // Convert DistrictDTO to DistrictEntity
        DistrictEntity districtEntity = districtMapper.convertToEntity(districtDTO);

        // Save the district entity to the database
        districtRepository.save(districtEntity);
    }
}