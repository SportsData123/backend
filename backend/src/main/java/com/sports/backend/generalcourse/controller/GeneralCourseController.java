package com.sports.backend.generalcourse.controller;

import com.sports.backend.common.ApiResponse;
import com.sports.backend.generalcourse.service.GeneralCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/general-courses")
@RequiredArgsConstructor
public class GeneralCourseController {
    private final GeneralCourseService generalCourseService;

    @PostMapping("/import")
    public ResponseEntity<ApiResponse<Void>> importGeneralCourses(
            @RequestParam String serviceKey,
            @RequestParam(defaultValue = "10") int numOfRows) {
        generalCourseService.fetchAndSaveGeneralCourses(serviceKey, numOfRows);
        return ResponseEntity.ok(new ApiResponse<>(200, "장애인 시설 데이터가 성공적으로 저장되었습니다.", null));
    }
}
