package com.sports.backend.course.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
public class CourseResponseDto {
    private int courseId;        // 장애인/일반 강좌 ID
    private String busiRegNo;    // 사업자 등록번호
    private String sportName;    // 종목명
    private String courseName;   // 강좌명
    private LocalTime startTime; // 시작 시간
    private LocalTime endTime;   // 종료 시간
    private String weekday;      // 요일
    private String description;  // 상세 설명
    private String fee;          // 요금 (disabled_course에만 있음)
    private String isAccessibleForDisabled;
    private String cityName;      // 도시 이름
    private String districtName;  // 구 이름
    private String roadAddr;      // 도로명 주소
    private String faciDaddr;     // 상세 주소
    private Double latitude;      // 위도
    private Double longitude;     // 경도
}
