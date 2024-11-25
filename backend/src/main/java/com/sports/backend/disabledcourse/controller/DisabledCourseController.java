package com.sports.backend.disabledcourse.controller;

import com.sports.backend.common.ApiResponse;
import com.sports.backend.disabledcourse.service.DisabledCourseService;
import com.sports.backend.disabledfacility.service.DisabledFacilityService;
import com.sports.backend.facility.dto.FacilityResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disabled-courses")
@RequiredArgsConstructor
public class DisabledCourseController {
    private final DisabledCourseService disabledCourseService;

    @PostMapping("/import")
    public ResponseEntity<ApiResponse<Void>> importDisabledCourses(
            @RequestParam String serviceKey,
            @RequestParam(defaultValue = "10") int numOfRows) {
        disabledCourseService.fetchAndSaveDisabledCourses(serviceKey, numOfRows);
        return ResponseEntity.ok(new ApiResponse<>(200, "장애인 시설 데이터가 성공적으로 저장되었습니다.", null));
    }
}
