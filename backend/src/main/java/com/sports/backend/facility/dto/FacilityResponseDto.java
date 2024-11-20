package com.sports.backend.facility.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FacilityResponseDto {
    private int facilityId;
    private String facilityName;
    private String facilityType;
    private String facilityStatus;
    private String roadAddress;
    private double latitude;
    private double longitude;
    private char isAccessibleForDisabled;
}
