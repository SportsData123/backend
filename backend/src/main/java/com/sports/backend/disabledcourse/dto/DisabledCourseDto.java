package com.sports.backend.disabledcourse.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;

/**
 * `DisabledCourseDto`는 장애인 강좌 데이터를 전송하기 위한 DTO(Data Transfer Object) 클래스입니다.
 * 엔티티와의 직접적인 매핑보다는 필요한 데이터만 담아 클라이언트와 통신할 때 사용됩니다.
 */
@Data
@Builder
public class DisabledCourseDto {
    private int disabledCourseId;
    private String busiRegNo;
    private String sportNm;
    private String courseNm;
    private LocalTime startTime;
    private LocalTime endTime;
    private String weekday;
    private String courseSetaDesc;
    private String settlAmt;
    private String roadAddr;
    private String faciDaddr;
    private String districtName;
    private String cityName;
    private Double latitude;
    private Double longitude;
}
