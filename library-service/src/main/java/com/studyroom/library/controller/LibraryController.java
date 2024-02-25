package com.studyroom.library.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.studyroom.library.model.dto.LibraryDTO;
import com.studyroom.library.service.LibraryService;

@RestController
public class LibraryController {

    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/nearest-libraries")
    public ResponseEntity<List<LibraryDTO>> getNearestLibraries(
            @RequestParam("userLat") double userLat,
            @RequestParam("userLon") double userLon,
            @RequestParam("districtId") Long districtId,
            @RequestParam(value = "localityIds", required = false) List<Long> localityIds,
            @RequestParam(value = "limit", defaultValue = "10") int limit) {

        List<LibraryDTO> nearestLibraries = libraryService.findNearestLibraries(userLat, userLon, districtId, localityIds, limit);
        return new ResponseEntity<>(nearestLibraries, HttpStatus.OK);
    }
}
