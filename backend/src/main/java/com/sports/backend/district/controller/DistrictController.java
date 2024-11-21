package com.sports.backend.district.controller;

import com.sports.backend.common.ApiResponse;
import com.sports.backend.district.dto.DistrictResponseDto;
import com.sports.backend.district.service.DistrictService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/districts")
public class DistrictController {
    private final DistrictService districtService;

    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<DistrictResponseDto>>> getDistricts() {
        List<DistrictResponseDto> districts = districtService.getDistrictList();
        return ResponseEntity.ok(new ApiResponse<>(200, "지역 목록 조회 성공", districts));
    }

    @PostMapping("/import")
    public ResponseEntity<ApiResponse<Void>> importDistricts() {
        districtService.importDistrictData("city_file.csv");
        return ResponseEntity.ok(new ApiResponse<>(200, "지역 데이터가 성공적으로 저장되었습니다.", null));
    }
}
