package com.sports.backend.disabledcourse.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;

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
