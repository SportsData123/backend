package com.sports.backend.disabledcourse.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DisabledCourseDto {
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
