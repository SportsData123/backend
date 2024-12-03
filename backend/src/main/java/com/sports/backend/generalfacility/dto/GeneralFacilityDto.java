package com.sports.backend.generalfacility.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GeneralFacilityDto {
    private String facilName;
    private String resTelno;
    private String mainEventName;
    private String cityCode;
    private String cityName;
    private String districtCode;
    private String districtName;
    private String brno;
    private String facilSn;
    private String roadAddr;
    private String faciDaddr;
}
