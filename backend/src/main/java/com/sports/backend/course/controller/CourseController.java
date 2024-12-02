package com.sports.backend.course.controller;

import com.sports.backend.common.ApiResponse;
import com.sports.backend.course.dto.CourseResponseDto;
import com.sports.backend.course.dto.PaginatedCourseResponseDto;
import com.sports.backend.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<ApiResponse<PaginatedCourseResponseDto>> getFilteredCourses(
            @RequestParam(required = false) String isAccessibleForDisabled,
            @RequestParam(required = false) String weekday,
            @RequestParam(required = false) String sportName,
            @RequestParam(required = false) String startTime,
            @RequestParam(required = false) String endTime,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        try {
            int pageNumber = page > 0 ? page - 1 : 0;
            PaginatedCourseResponseDto courses = courseService.getAllCourses(
                    pageNumber, size, isAccessibleForDisabled, weekday, sportName, startTime, endTime);
            return ResponseEntity.ok(new ApiResponse<>(200, "강좌 목록 조회 성공", courses));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(500, "강좌 목록 조회 실패: " + e.getMessage(), null));
        }
    }
}
