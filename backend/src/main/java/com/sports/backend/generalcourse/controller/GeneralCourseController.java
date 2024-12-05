package com.sports.backend.generalcourse.controller;

import com.sports.backend.common.ApiResponse;
import com.sports.backend.generalcourse.service.GeneralCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * `GeneralCourseController`는 일반 강좌 데이터를 관리하는 API 엔드포인트를 제공하는 컨트롤러 클래스입니다.
 * 클라이언트는 이 컨트롤러를 통해 일반 강좌 데이터를 외부 API로부터 가져와 데이터베이스에 저장할 수 있습니다.
 */
@RestController
@RequestMapping("/api/general-courses")
@RequiredArgsConstructor
public class GeneralCourseController {

    private final GeneralCourseService generalCourseService;

    /**
     * 외부 API를 호출하여 일반 강좌 데이터를 가져와 데이터베이스에 저장하는 엔드포인트입니다.
     *
     * @param serviceKey API 호출에 필요한 인증 키
     * @param numOfRows  한 페이지에 가져올 데이터 개수 (기본값: 10)
     * @return 데이터 저장 성공 여부를 나타내는 표준 응답 구조
     */
    @PostMapping("/import")
    public ResponseEntity<ApiResponse<Void>> importGeneralCourses(
            @RequestParam String serviceKey,
            @RequestParam(defaultValue = "10") int numOfRows) {

        generalCourseService.fetchAndSaveGeneralCourses(serviceKey, numOfRows);
        return ResponseEntity.ok(new ApiResponse<>(200, "장애인 시설 데이터가 성공적으로 저장되었습니다.", null));
    }
}
