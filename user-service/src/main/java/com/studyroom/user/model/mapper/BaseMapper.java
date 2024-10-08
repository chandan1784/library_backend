package com.studyroom.user.model.mapper;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class BaseMapper<E, D> {
	public abstract E convertToEntity(D dto, Object... args);

	public abstract D convertToDto(E entity, Object... args);

	public Collection<E> convertToEntity(Collection<D> dto, Object... args) {
		return dto.stream().map(d -> convertToEntity(d, args)).collect(Collectors.toList());
		// d(This is the parameter of the anonymous function. It represents each individual element (DTO) in the Stream as it's being processed.)->(This arrow denotes a lambda expression, separating the parameter list from the body of the function.)
	}
	
	
	/*
	// Without lambda expression, implement the anonymous function "Function" and it has one method that is "apply". 
	public Collection<E> convertToEntity(Collection<D> dto, Object... args) {
	    return dto.stream().map(new Function<D, E>() {
	        @Override
	        public E apply(D d) {
	            return convertToEntity(d, args);
	        }
	    }).collect(Collectors.toList());
	}*/

	
	/*//Traditional way
	  public Collection<E> convertToEntity(Collection<D> dto, Object... args) {
	    // Create a new list to store the resulting entities
	    List<E> entities = new ArrayList<>();

	    // Iterate over each DTO in the input collection
	    for (D d : dto) {
	        // Convert the current DTO to an entity and add it to the list of entities
	        E entity = convertToEntity(d, args);
	        entities.add(entity);
	    }

	    // Return the list of entities
	    return entities;
	}*/
	
	public Collection<D> convertToDto(Collection<E> entity, Object... args) {
		return entity.stream().map(e -> convertToDto(e, args)).collect(Collectors.toList());
	}

	public List<E> convertToEntityList(Collection<D> dto, Object... args) {
		return convertToEntity(dto, args).stream().collect(Collectors.toList());
	}

	public List<D> convertToDtoList(Collection<E> entity, Object... args) {
		return convertToDto(entity, args).stream().collect(Collectors.toList());
	}

	public Set<E> convertToEntitySet(Collection<D> dto, Object... args) {
		return convertToEntity(dto, args).stream().collect(Collectors.toSet());
	}

	public Set<D> convertToDtoSet(Collection<E> entity, Object... args) {
		return convertToDto(entity, args).stream().collect(Collectors.toSet());
	}

}
