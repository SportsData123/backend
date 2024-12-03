package com.sports.backend.generalfacility.controller;

import com.sports.backend.common.ApiResponse;
import com.sports.backend.disabledfacility.service.DisabledFacilityService;
import com.sports.backend.facility.dto.FacilityResponseDto;
import com.sports.backend.generalfacility.service.GeneralFacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/general-facilities")
@RequiredArgsConstructor
public class GeneralFacilityController {
    private final GeneralFacilityService generalFacilityService;

    @PostMapping("/import")
    public ResponseEntity<ApiResponse<Void>> importGeneralFacilities(
            @RequestParam String serviceKey,
            @RequestParam(defaultValue = "10") int numOfRows) {
        try {
            generalFacilityService.fetchAndSaveGeneralFacilities(serviceKey, numOfRows);
            return ResponseEntity.ok(new ApiResponse<>(200, "일반 시설 데이터가 성공적으로 저장되었습니다.", null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(500, "Failed to import data: " + e.getMessage(), null));
        }
    }
}
