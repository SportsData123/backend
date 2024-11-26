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
}
