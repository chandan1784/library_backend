package com.studyroom.library.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.studyroom.library.model.dto.DistrictDTO;
import com.studyroom.library.model.dto.LibraryDTO;
import com.studyroom.library.model.dto.LocalityDTO;
import com.studyroom.library.service.DistrictService;
import com.studyroom.library.service.LibraryService;
import com.studyroom.library.service.LocalityService;

@RestController
@RequestMapping("/libraries")
public class LibraryController {

    private final LibraryService libraryService;
    private final DistrictService districtService;
    private final LocalityService localityService;

    public LibraryController(LibraryService libraryService, DistrictService districtService, LocalityService localityService) {
        this.libraryService = libraryService;
        this.districtService = districtService;
        this.localityService = localityService;
    }
    
    @PostMapping("/add-library")
    public ResponseEntity<?> addLibrary(@RequestBody LibraryDTO libraryDTO) {
        libraryService.addLibrary(libraryDTO);
        return ResponseEntity.ok("Library added successfully");
    }

    @PostMapping("/districts")
    public ResponseEntity<?> addDistrict(@RequestBody DistrictDTO districtDTO) {
        districtService.addDistrict(districtDTO);
        return ResponseEntity.ok("District added successfully");
    }
    

    @PostMapping("/localities")
    public ResponseEntity<?> addLocality(@RequestBody LocalityDTO localityDTO) {
        localityService.addLocality(localityDTO);
        return ResponseEntity.ok("Locality added successfully");
    }

    @GetMapping("/nearest-libraries/backend-test")
    public ResponseEntity<List<LibraryDTO>> getNearestLibrariesBackendTest(
            @RequestParam("userLat") double userLat,
            @RequestParam("userLon") double userLon,
            @RequestParam(value = "districtId",required = false) Long districtId,
            @RequestParam(value = "localityIds", required = false) List<Long> localityIds,
            @RequestParam(value = "limit", defaultValue = "10") int limit) {

        List<LibraryDTO> nearestLibraries = libraryService.findNearestLibrariesBackendTest(userLat, userLon, districtId, localityIds, limit);
        return ResponseEntity.ok(nearestLibraries);
    }
    
    @GetMapping("/nearest-libraries")
    public ResponseEntity<String> getNearestLibraries(
            @RequestParam("userLat") double userLat,
            @RequestParam("userLon") double userLon,
            @RequestParam(value = "districtName",required = false) String districtName,
            @RequestParam(value = "localityName", required = false) String localityName,
            @RequestParam(value = "limit", defaultValue = "10") int limit) {

        String nearestLibraries = libraryService.findNearestLibraries(userLat, userLon, districtName, localityName, limit);
        
        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create ResponseEntity with headers and status code
        return new ResponseEntity<>(nearestLibraries, headers, HttpStatus.OK);

    }
    @GetMapping("/library-test")
	public String libraryTest() {
		return "You are calling library microserice";
	}
}
