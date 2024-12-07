package com.sports.backend.course.controller;

import com.sports.backend.common.ApiResponse;
import com.sports.backend.course.dto.PaginatedCourseResponseDto;
import com.sports.backend.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * CourseController는 강좌(course) 관련 API 요청을 처리하는 컨트롤러 클래스입니다.
 * 클라이언트가 강좌 데이터를 조회하거나 필터링된 강좌 목록을 요청할 수 있습니다.
 */
@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    // CourseService 객체를 주입받아 사용
    private final CourseService courseService;

    /**
     * 필터링 조건에 따라 강좌 목록을 조회하는 엔드포인트입니다.
     * 페이징 기능을 제공하며, 요청 파라미터를 사용해 필터링 옵션을 지정할 수 있습니다.
     *
     * @param isAccessibleForDisabled 장애인 접근 가능 여부 (optional)
     * @param weekday 요일 필터 (optional)
     * @param sportName 스포츠 이름 필터 (optional)
     * @param startTime 강좌 시작 시간 필터 (optional)
     * @param endTime 강좌 종료 시간 필터 (optional)
     * @param page 조회할 페이지 번호 (기본값: 1)
     * @param size 페이지당 강좌 수 (기본값: 10)
     * @return ResponseEntity<ApiResponse<PaginatedCourseResponseDto>> 강좌 목록 응답
     */
    @GetMapping
    public ResponseEntity<ApiResponse<PaginatedCourseResponseDto>> getFilteredCourses(
            @RequestParam(required = false) String isAccessibleForDisabled,
            @RequestParam(required = false) String weekday,
            @RequestParam(required = false) String sportName,
            @RequestParam(required = false) String startTime,
            @RequestParam(required = false) String endTime,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {

        System.out.println("isAccessibleForDisabled: " + isAccessibleForDisabled);


        // 페이지 번호가 1 이상이어야 하므로 1 미만인 경우 0으로 설정
        int pageNumber = page > 0 ? page - 1 : 0;

        // 서비스 호출하여 필터링된 강좌 목록 조회
        PaginatedCourseResponseDto courses = courseService.getAllCourses(
                pageNumber, size, isAccessibleForDisabled, weekday, sportName, startTime, endTime);

        // 성공적인 응답 반환
        return ResponseEntity.ok(new ApiResponse<>(200, "강좌 목록 조회 성공", courses));

    }

}
