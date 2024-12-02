package com.sports.backend.city.controller;

import com.sports.backend.city.dto.CityResponseDto;
import com.sports.backend.city.service.CityService;
import com.sports.backend.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CityResponseDto>>> getCities() {
        try {
            List<CityResponseDto> cities = cityService.getCityList();
            return ResponseEntity.ok(new ApiResponse<>(200, "도시 목록 조회 성공", cities));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(500, "Failed to import data: " + e.getMessage(), null));
        }
    }

    @PostMapping("/import")
    public ResponseEntity<ApiResponse<List<CityResponseDto>>> importCities() {
        cityService.importCityData("city_file.csv ");
        return ResponseEntity.ok(new ApiResponse<>(200, "도시 데이터가 성공적으로 저장되었습니다.", null));
    }
}