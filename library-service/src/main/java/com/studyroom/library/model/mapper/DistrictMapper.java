package com.studyroom.library.model.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.studyroom.library.model.dto.DistrictDTO;
import com.studyroom.library.model.entity.DistrictEntity;

@Component
public class DistrictMapper extends BaseMapper<DistrictEntity, DistrictDTO> {

	@Override
	public DistrictEntity convertToEntity(DistrictDTO dto, Object... args) {
		DistrictEntity districtEntity = new DistrictEntity();
		if (dto != null) {
			BeanUtils.copyProperties(dto, districtEntity);
		}
		return districtEntity;
	}

	@Override
	public DistrictDTO convertToDto(DistrictEntity entity, Object... args) {
		DistrictDTO districtDTO = new DistrictDTO();
		if (entity != null) {
			BeanUtils.copyProperties(entity, districtDTO);
		}
		return districtDTO;
	}
}
