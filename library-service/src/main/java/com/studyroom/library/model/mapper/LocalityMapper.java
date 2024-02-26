package com.studyroom.library.model.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.studyroom.library.model.dto.LocalityDTO;
import com.studyroom.library.model.entity.LocalityEntity;

@Component
public class LocalityMapper extends BaseMapper<LocalityEntity, LocalityDTO> {

	@Override
	public LocalityEntity convertToEntity(LocalityDTO dto, Object... args) {
		LocalityEntity localityEntity = new LocalityEntity();
		if (dto != null) {
			BeanUtils.copyProperties(dto, localityEntity);
		}
		return localityEntity;
	}

	@Override
	public LocalityDTO convertToDto(LocalityEntity entity, Object... args) {
		LocalityDTO localityDTO = new LocalityDTO();
		if (entity != null) {
			BeanUtils.copyProperties(entity, localityDTO);
		}
		return localityDTO;
	}
}

