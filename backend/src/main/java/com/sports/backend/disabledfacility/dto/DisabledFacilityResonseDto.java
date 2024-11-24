package com.sports.backend.disabledfacility.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DisabledFacilityResonseDto {
    private int disabledFacilityId;
    private String facilName;
    private String resTelno;
    private String mainEventName;
    private String cityCode;
    private String cityName;
    private String districtCode;
    private String districtName;
}
