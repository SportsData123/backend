package com.sports.backend.disabledfacility.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DisabledFacilityDto {
    private String facilName;
    private String resTelno;
    private String mainEventName;
    private String cityCode;
    private String cityName;
    private String districtCode;
    private String districtName;
    private String roadAddr;
    private String faciDaddr;
}
