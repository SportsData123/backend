package com.sports.backend.disabledcourse.controller;

import com.sports.backend.common.ApiResponse;
import com.sports.backend.disabledcourse.service.DisabledCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * DisabledCourseController는 장애인 강좌 데이터를 관리하기 위한 API를 제공합니다.
 * 장애인 강좌 데이터를 외부 서비스에서 가져와 저장하는 기능을 포함합니다.
 */
@RestController
@RequestMapping("/api/disabled-courses")
@RequiredArgsConstructor
public class DisabledCourseController {

    private final DisabledCourseService disabledCourseService;

    /**
     * 외부 서비스에서 장애인 강좌 데이터를 가져와 저장하는 API.
     *
     * @param serviceKey 외부 API 호출에 필요한 서비스 키
     * @param numOfRows  가져올 데이터의 개수 (기본값: 10)
     * @return ApiResponse<Void> 데이터 저장 결과 메시지를 포함한 응답
     */
    @PostMapping("/import")
    public ResponseEntity<ApiResponse<Void>> importDisabledCourses(
            @RequestParam String serviceKey,
            @RequestParam(defaultValue = "10") int numOfRows) {
        disabledCourseService.fetchAndSaveDisabledCourses(serviceKey, numOfRows);
        return ResponseEntity.ok(new ApiResponse<>(200, "장애인 시설 데이터가 성공적으로 저장되었습니다.", null));
    }
}
