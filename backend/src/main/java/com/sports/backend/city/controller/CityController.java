package com.sports.backend.city.controller;

import com.sports.backend.city.dto.CityResponseDto;
import com.sports.backend.city.service.CityService;
import com.sports.backend.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CityController는 도시와 관련된 API 요청을 처리하는 컨트롤러입니다.
 * GET 요청을 통해 도시 목록을 조회할 수 있습니다.
 * POST 요청은 도시 데이터를 가져와 저장하기 위해 만들었습니다.
 */

@RestController
@RequestMapping("/api/cities")
public class CityController {
    private final CityService cityService;

    // CityService를 DI(의존성 주입)하여 초기화합니다.
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    /**
     * 도시 목록 조회 API
     *
     * @return ResponseEntity<ApiResponse<List<CityResponseDto>>> - 도시 목록 및 상태 메시지를 포함한 응답
     */

    @GetMapping
    public ResponseEntity<ApiResponse<List<CityResponseDto>>> getCities() {
        try {
            List<CityResponseDto> cities = cityService.getCityList();
            return ResponseEntity.ok(new ApiResponse<>(200, "도시 목록 조회 성공", cities));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(500, "Failed to import data: " + e.getMessage(), null));
        }
    }



    /**
     * 도시 데이터 가져오기 및 저장 API
     *
     * @return ResponseEntity<ApiResponse<List<CityResponseDto>>> - 저장 상태 메시지를 포함한 응답
     */

    @PostMapping("/import")
    public ResponseEntity<ApiResponse<List<CityResponseDto>>> importCities() {
        cityService.importCityData("city_file.csv ");
        return ResponseEntity.ok(new ApiResponse<>(200, "도시 데이터가 성공적으로 저장되었습니다.", null));
    }
}