package com.sports.backend.disabledcourse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DisabledCourseResponseDto {
    private int disabledCourseId;
    private String busiRegNo;
    private String sportNm;
    private String courseNm;
    private String startTime;
    private String endTime;
    private String weekday;
    private String courseSetaDesc;
    private String settlAmt;
}
