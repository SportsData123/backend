package com.sports.backend.facility.controller;

import com.sports.backend.common.ApiResponse;
import com.sports.backend.facility.dto.FacilityResponseDto;
import com.sports.backend.facility.service.FacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/facilities")
@RequiredArgsConstructor
public class FacilityController {
    private final FacilityService facilityService;

    @PostMapping("/importAll")
    public ResponseEntity<ApiResponse<Void>> importAllFacilities(
            @RequestParam String serviceKey,
            @RequestParam(defaultValue = "10") int numOfRows) {
        facilityService.fetchAndSaveAllFacilities(serviceKey, numOfRows);
        return ResponseEntity.ok(new ApiResponse<>(200, "모든 시설 데이터가 성공적으로 저장되었습니다.", null));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<FacilityResponseDto>>> getFacilities(
            @RequestParam(required = false) String cityId,
            @RequestParam(required = false) String districtId,
            @RequestParam(required = false) String isAccessibleForDisabled,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            List<FacilityResponseDto> facilities = facilityService.getFacilities(cityId, districtId, isAccessibleForDisabled, page, size);
            return ResponseEntity.ok(new ApiResponse<>(200, "시설 데이터 조회 성공", facilities));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(500, "시설 데이터 조회 실패: " + e.getMessage(), null));
        }
    }

    @GetMapping("/pagination-info")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getPaginationInfo(
            @RequestParam(required = false) String cityId,
            @RequestParam(required = false) String districtId,
            @RequestParam(required = false) String isAccessibleForDisabled,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Map<String, Object> paginationInfo = facilityService.getPaginationInfo(cityId, districtId, isAccessibleForDisabled, size);
            return ResponseEntity.ok(new ApiResponse<>(200, "페이지네이션 정보 조회 성공", paginationInfo));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(500, "페이지네이션 정보 조회 실패: " + e.getMessage(), null));
        }
    }
}