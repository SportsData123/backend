package com.sports.backend.generalcourse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class GeneralCourseResponseDto {
    private int generalCourseId;
    private String busiRegNo;
    private String sportNm;
    private String courseNm;
    private LocalTime startTime;
    private LocalTime endTime;
    private String weekday;
    private String courseSetaDesc;
}
