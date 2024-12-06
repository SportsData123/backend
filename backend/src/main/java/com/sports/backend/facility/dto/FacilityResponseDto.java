package com.sports.backend.facility.dto;

import com.sports.backend.course.dto.CourseResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FacilityResponseDto {
    private Integer facilityId;
    private String facilityName;
    private String facilityType;
    private String facilityStatus;
    private String roadAddress;
    private String detailAddress;
    private String zipCode;
    private Double longitude;
    private Double latitude;
    private String inOutType;
    private String nationFlag;
    private String cityName;
    private String districtName;

    // GeneralFacility specific fields
    private Integer generalFacilityId;
    private String generalBrno;
    private String generalFacilSn;
    private String generalResTelno;
    private String generalMainEventName;

    // DisabledFacility specific fields
    private Integer disabledFacilityId;
    private String disabledResTelno;
    private String disabledMainEventName;
    private String isAccessibleForDisabled;

    private List<CourseResponseDto> courses;
}