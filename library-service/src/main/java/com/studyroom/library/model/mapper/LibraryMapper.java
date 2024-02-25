package com.studyroom.library.model.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.studyroom.library.model.dto.LibraryDTO;
import com.studyroom.library.model.entity.LibraryEntity;

@Component
public class LibraryMapper extends BaseMapper<LibraryEntity, LibraryDTO> {

	@Override
	public LibraryEntity convertToEntity(LibraryDTO dto, Object... args) {
		LibraryEntity userEntity = new LibraryEntity();
		if (dto != null) {
			BeanUtils.copyProperties(dto, userEntity);
		}
		return userEntity;
	}

	@Override
	public LibraryDTO convertToDto(LibraryEntity entity, Object... args) {
		LibraryDTO user = new LibraryDTO();
		if (entity != null) {
			BeanUtils.copyProperties(entity, user);
		}
		return user;
	}
}
