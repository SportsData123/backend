package com.sports.backend.facility.controller;

import com.sports.backend.api.service.ApiService;
import com.sports.backend.common.ApiResponse;
import com.sports.backend.facility.domain.Facility;
import com.sports.backend.facility.dto.FacilityPagedResponseDto;
import com.sports.backend.facility.dto.FacilityRequestDto;
import com.sports.backend.facility.service.FacilityImportService;
import com.sports.backend.facility.service.FacilityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 시설 API 엔드포인트
@RestController
@RequestMapping("/api/facilities")
public class FacilityController {
    private final FacilityService facilityService;
    private final ApiService apiService;
    private final FacilityImportService facilityImportService;


    public FacilityController(FacilityService facilityService, ApiService apiService, FacilityImportService facilityImportService) {
        this.facilityService = facilityService;
        this.apiService = apiService;
        this.facilityImportService = facilityImportService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<FacilityPagedResponseDto>> getFacilities(
            @RequestParam String cityCode,
            @RequestParam String districtCode,
            @RequestParam(required = false) Character isAccessibleForDisabled,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        // FacilityRequestDto 생성
        FacilityRequestDto requestDto = new FacilityRequestDto(cityCode, districtCode, isAccessibleForDisabled, page, size);

        // 서비스 호출
        FacilityPagedResponseDto responseDto = (FacilityPagedResponseDto) facilityService.getFacilities(requestDto);

        // ApiResponse 생성
        ApiResponse<FacilityPagedResponseDto> apiResponse = new ApiResponse<>(
                200,
                "체육시설 목록 조회 성공",
                responseDto
        );

        // 공통 응답 반환
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/import")
    public ResponseEntity<ApiResponse<String>> importFacilitiesFromApi(
            @RequestParam int pageNo, @RequestParam int numOfRows) {
        List<Facility> facilities = apiService.fetchAndSaveFacilityData(pageNo, numOfRows);

        try {
            facilityImportService
                    .importFacilities(pageNo, numOfRows);
            return ResponseEntity.ok(new ApiResponse<>(200, "데이터 가져오기 성공", "시설 데이터가 성공적으로 저장되었습니다."));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(500, "데이터 가져오기 실패", e.getMessage()));
        }
    }
}