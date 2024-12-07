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
    private int courseId;
    private String busiRegNo;
    private String sportName;
    private String courseName;
    private LocalTime startTime;
    private LocalTime endTime;
    private String weekday;
    private String description;
    private String isAccessibleForDisabled;
    private String facilSn;
    private String fee;
    private String roadAddr;
    private String faciDaddr;
    private String districtName;
    private String districtCode;
    private Integer districtId;
    private String cityName;
    private String cityCode;
    private Integer cityId;
    private Double latitude;
    private Double longitude;
    private Integer facilityId;
}
