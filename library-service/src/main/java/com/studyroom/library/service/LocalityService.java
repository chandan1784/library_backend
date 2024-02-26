package com.studyroom.library.service;

import org.springframework.stereotype.Service;

import com.studyroom.library.model.dto.LocalityDTO;
import com.studyroom.library.model.entity.LocalityEntity;
import com.studyroom.library.model.mapper.LocalityMapper;
import com.studyroom.library.repository.LocalityRepository;

@Service
public class LocalityService {

    private final LocalityRepository localityRepository;
    private final LocalityMapper localityMapper;

    public LocalityService(LocalityRepository localityRepository, LocalityMapper localityMapper) {
        this.localityRepository = localityRepository;
        this.localityMapper = localityMapper;
    }

    public void addLocality(LocalityDTO localityDTO) {
        // Convert LocalityDTO to LocalityEntity
        LocalityEntity localityEntity = localityMapper.convertToEntity(localityDTO);

        // Save the locality entity to the database
        localityRepository.save(localityEntity);
    }
}