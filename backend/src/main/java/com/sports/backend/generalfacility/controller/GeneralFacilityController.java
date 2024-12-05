package com.sports.backend.generalfacility.controller;

import com.sports.backend.common.ApiResponse;
import com.sports.backend.generalfacility.service.GeneralFacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * `GeneralFacilityController`는 일반 시설 관련 API 요청을 처리하는 컨트롤러 클래스입니다.
 * 외부 API 호출을 통해 데이터를 가져와 저장하는 기능을 제공합니다.
 * 클라이언트는 이 컨트롤러를 통해 일반 시설 데이터를 가져오는 요청을 할 수 있습니다.
 */
@RestController
@RequestMapping("/api/general-facilities")
@RequiredArgsConstructor
public class GeneralFacilityController {

    private final GeneralFacilityService generalFacilityService;

    /**
     * 외부 API를 호출하여 일반 시설 데이터를 가져오고 저장하는 엔드포인트입니다.
     *
     * @param serviceKey 외부 API 호출에 필요한 인증키
     * @param numOfRows  한 페이지에 가져올 데이터의 개수 (기본값: 10)
     * @return ResponseEntity<ApiResponse<Void>> 데이터 저장 성공 여부를 응답
     */
    @PostMapping("/import")
    public ResponseEntity<ApiResponse<Void>> importGeneralFacilities(
            @RequestParam String serviceKey,
            @RequestParam(defaultValue = "10") int numOfRows) {

        generalFacilityService.fetchAndSaveGeneralFacilities(serviceKey, numOfRows);
        return ResponseEntity.ok(new ApiResponse<>(200, "일반 시설 데이터가 성공적으로 저장되었습니다.", null));
    }
}
