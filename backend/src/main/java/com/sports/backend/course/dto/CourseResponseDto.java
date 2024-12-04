package com.sports.backend.course.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;

/**
 * CourseResponseDto는 강좌 정보를 응답으로 제공하기 위한 DTO(Data Transfer Object) 클래스입니다.
 * 강좌 관련 데이터를 클라이언트로 전달하는 데 사용됩니다.
 */
@Data
@Builder
@AllArgsConstructor
public class CourseResponseDto {
    private int courseId;        // 강좌 ID (장애인/일반 강좌를 구분하지 않고 공통적으로 사용)
    private String busiRegNo;    // 사업자 등록번호
    private String sportName;    // 종목명
    private String courseName;   // 강좌명
    private LocalTime startTime; // 시작 시간
    private LocalTime endTime;   // 종료 시간
    private String weekday;      // 요일
    private String description;  // 상세 설명
    private String fee;          // 요금
    private String isAccessibleForDisabled; // 장애인 접근 가능 여부 (Y/N)
    private String cityName;      // 도시 이름
    private String districtName;  // 구 이름
    private String roadAddr;      // 도로명 주소
    private String faciDaddr;     // 상세 주소
    private Double latitude;      // 위도
    private Double longitude;     // 경도
}
