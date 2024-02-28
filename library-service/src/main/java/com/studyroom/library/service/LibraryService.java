package com.studyroom.library.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
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

	public List<LibraryDTO> findNearestLibrariesBackendTest(double userLat, double userLon, Long districtId,
			List<Long> localityIds, int limit) {
		List<LibraryEntity> libraries;

		// If localityIds and DistrictIds are not provided
		if (!(districtId != null & (localityIds != null && !localityIds.isEmpty()))) {
			libraries = libraryRepository.findAll();
		} else if (localityIds != null && !localityIds.isEmpty() && districtId != null) {
			libraries = libraryRepository.findByDistrictIdAndLocalityIdIn(districtId, localityIds);
		} else if (districtId != null && !(localityIds != null && !localityIds.isEmpty())) {
			libraries = libraryRepository.findByDistrictId(districtId);
		}
		// Otherwise, fetch libraries only within the specified localities
		else {
			libraries = libraryRepository.findByLocalityId(districtId);
		}

		Map<LibraryEntity, Double> distances = calculateDistances(userLat, userLon, libraries);

		return getNearestLibraries(distances, limit);

	}

	public String findNearestLibraries(double userLat, double userLon, String districtName,
			String localityName, int limit) {
		List<LibraryEntity> libraries;
		// If localityIds and DistrictIds are not provided
		if (districtName != null & localityName != null) {
			libraries = libraryRepository.findAll();
		} else if (localityName != null && districtName != null) {
			libraries = libraryRepository.findByDistrictNameAndLocalityNameContainingIgnoreCase(districtName, localityName);
		} else if (districtName != null && !(localityName != null)) {
			libraries = libraryRepository.findByDistrictNameContainingIgnoreCase(districtName);
		} else {
			libraries = libraryRepository.findByLocalityNameContainingIgnoreCase(localityName);

		}
		Map<LibraryEntity, Double> distances = calculateDistances(userLat, userLon, libraries);
		
		return convertListToJson(getNearestLibraries(distances, limit));
		
	}

	// Add Libraries
	public void addLibrary(LibraryDTO libraryDTO) {
		// Convert LibraryDTO to LibraryEntity
		LibraryEntity libraryEntity = libraryMapper.convertToEntity(libraryDTO);

		// Save the library entity to the database
		libraryRepository.save(libraryEntity);
	}

	// Calculate distances between user location and each library
	public Map<LibraryEntity, Double> calculateDistances(double userLat, double userLon,
			List<LibraryEntity> libraries) {
		Map<LibraryEntity, Double> distances = new HashMap<>();
		for (LibraryEntity library : libraries) {
			double libraryLat = library.getLat();
			double libraryLon = library.getLon();
			double distance = calculateDistance(userLat, userLon, libraryLat, libraryLon);
			distances.put(library, distance);
		}
		return distances;
	}

	// Get Nearest Libraries by sorting the distances and return the
	// nearestLibraries based on the limit set
	public List<LibraryDTO> getNearestLibraries(Map<LibraryEntity, Double> distances, int limit) {
		List<Map.Entry<LibraryEntity, Double>> sortedDistances = distances.entrySet().stream()
				.sorted(Map.Entry.comparingByValue()).collect(Collectors.toList());

		List<LibraryEntity> nearestLibraries = sortedDistances.stream().map(Map.Entry::getKey).limit(limit)
				.collect(Collectors.toList());

		return libraryMapper.convertToDtoList(nearestLibraries);
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
	
	public String convertListToJson(List<LibraryDTO> libraryDTOList) {
		System.out.println("inside convertListToJson ");
		ObjectMapper objectMapper = new ObjectMapper();

		List<String> jsonList = new ArrayList<>();

		// Iterate over the list and serialize each object individually
		for (LibraryDTO obj : libraryDTOList) {
			try {
				String json = objectMapper.writeValueAsString(obj);
				jsonList.add(json);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}

		// Concatenate all JSON strings into a single JSON array string
		StringBuilder combinedJson = new StringBuilder("{ \"results\": [");
		for (String jsonString : jsonList) {
			combinedJson.append(jsonString).append(",");
		}
		// Remove the last comma
		if (!jsonList.isEmpty()) {
			combinedJson.deleteCharAt(combinedJson.length() - 1);
		}
		combinedJson.append("]}");

		return combinedJson.toString();
		    
	    /*ObjectMapper objectMapper = new ObjectMapper();

	    Map<String, String> jsonMap = new HashMap<>();
	
	    // Iterate over the list and serialize each object individually
	    int index = 1;
	    for (LibraryDTO obj : libraryDTOList) {
	        try {
	            String json = objectMapper.writeValueAsString(obj);
	            jsonMap.put(Integer.toString(index++), json);
	        } catch (JsonProcessingException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    // Concatenate all JSON strings into a single JSON object string
	    StringBuilder combinedJson = new StringBuilder("{");
	    for (Map.Entry<String, String> entry : jsonMap.entrySet()) {
	        combinedJson.append("\"").append(entry.getKey()).append("\":").append(entry.getValue()).append(",");
	    }
	    // Remove the last comma
	    if (!jsonMap.isEmpty()) {
	        combinedJson.deleteCharAt(combinedJson.length() - 1);
	    }
	    combinedJson.append("}");
	    
	    return combinedJson.toString();*/
	     
	    //jackson
			/*try {
				return objectMapper .writeValueAsString(libraryDTOList);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}*/
	    
//			Gson gson = new Gson();
//			return gson.toJson(libraryDTOList);
		}
}
