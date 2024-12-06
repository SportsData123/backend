package com.sports.backend.disabledfacility.controller;

import com.sports.backend.common.ApiResponse;
import com.sports.backend.disabledfacility.service.DisabledFacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * `DisabledFacilityController`는 장애인 시설 데이터를 관리하는 API를 제공합니다.
 * 외부 데이터를 가져와 데이터베이스에 저장하는 기능을 포함합니다.
 */
@RestController
@RequestMapping("/api/disabled-facilities")
@RequiredArgsConstructor
public class DisabledFacilityController {
    private final DisabledFacilityService disabledFacilityService;

    @PostMapping("/import")
    public ResponseEntity<ApiResponse<Void>> importDisabledFacilities(
            @RequestParam String serviceKey,
            @RequestParam(defaultValue = "10") int numOfRows) {
        try {
            disabledFacilityService.fetchAndSaveDisabledFacilities(serviceKey, numOfRows);
            return ResponseEntity.ok(new ApiResponse<>(200, "장애인 시설 데이터가 성공적으로 저장되었습니다.", null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(500, "Failed to import data: " + e.getMessage(), null));
        }
    }
}
