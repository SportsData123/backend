package com.sports.backend.facility.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FacilityResponseDto {
    private int facilityId;
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
}