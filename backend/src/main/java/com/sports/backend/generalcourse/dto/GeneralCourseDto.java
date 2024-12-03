package com.sports.backend.generalcourse.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;

@Data
@Builder
public class GeneralCourseDto {
    private int generalCourseId;
    private String busiRegNo;
    private String sportNm;
    private String courseNm;
    private LocalTime startTime;
    private LocalTime endTime;
    private String weekday;
    private String courseSetaDesc;
    private String facilSn;
    private String settlAmt;
    private String roadAddr;      // 도로명 주소
    private String faciDaddr;     // 시설 상세 주소
    private String districtName;  // 구 이름
    private String cityName;      // 도시 이름
    private Double latitude;      // 위도
    private Double longitude;     // 경도
}
