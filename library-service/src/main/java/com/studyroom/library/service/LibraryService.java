package com.studyroom.library.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.studyroom.library.model.dto.LibraryDTO;
import com.studyroom.library.model.entity.LibraryEntity;
import com.studyroom.library.model.mapper.LibraryMapper;
import com.studyroom.library.repository.LibraryRepository;

@Service			
public class LibraryService {

	private final LibraryRepository libraryRepository;
	private final LibraryMapper libraryMapper;
	
	public LibraryService(LibraryRepository libraryRepository, LibraryMapper libraryMapper) {
        this.libraryRepository = libraryRepository;
        this.libraryMapper = libraryMapper;
    }

	// Radius of the Earth in kilometers
	private static final double EARTH_RADIUS = 6371;

	public List<LibraryDTO> findNearestLibraries(double userLat, double userLon, Long districtId,
			List<Long> localityIds, int limit) {
		List<LibraryEntity> libraries;

		// If localityIds and DistrictIds are not provided
		if (!(districtId != null & (localityIds != null && !localityIds.isEmpty()))) {
			libraries = libraryRepository.findAll();
		} else if (localityIds != null && !localityIds.isEmpty()) {
			libraries = libraryRepository.findByDistrictIdAndLocalityIdIn(districtId, localityIds);// If localityIds are provided, fetch libraries within the specified district and localities
		} else {
			// Otherwise, fetch libraries only within the specified district
			libraries = libraryRepository.findByDistrictId(districtId);
		}

		// Calculate distances between user location and each library
		Map<LibraryEntity, Double> distances = new HashMap<>();
		for (LibraryEntity library : libraries) {
			double libraryLat = library.getLat();
			double libraryLon = library.getLon();
			double distance = calculateDistance(userLat, userLon, libraryLat, libraryLon);
			distances.put(library, distance);
		}

		// Sort libraries based on distances
		List<Map.Entry<LibraryEntity, Double>> sortedDistances = distances.entrySet().stream()
				.sorted(Map.Entry.comparingByValue()).collect(Collectors.toList());

		// Limit the number of libraries to return
		List<LibraryEntity> nearestLibraries = sortedDistances.stream().map(Map.Entry::getKey).limit(limit)
				.collect(Collectors.toList());

		// Convert LibraryEntity objects to LibraryDTO objects
		return libraryMapper.convertToDtoList(nearestLibraries);

	}
	
	//Add Libraries
	public void addLibrary(LibraryDTO libraryDTO) {
        // Convert LibraryDTO to LibraryEntity
        LibraryEntity libraryEntity = libraryMapper.convertToEntity(libraryDTO);

        // Save the library entity to the database
        libraryRepository.save(libraryEntity);
    }


	// Calculate distance between two coordinates using Haversine formula
	private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
		double dLat = Math.toRadians(lat2 - lat1);
		double dLon = Math.toRadians(lon2 - lon1);
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return EARTH_RADIUS * c;
	}
}

