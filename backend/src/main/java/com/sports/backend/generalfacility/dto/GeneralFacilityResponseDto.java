package com.sports.backend.generalfacility.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GeneralFacilityResponseDto {
    private String facilName;
    private String resTelno;
    private String mainEventName;
    private String cityCode;
    private String cityName;
    private String districtCode;
    private String districtName;
    private String brno;
    private String facilSn;
}
