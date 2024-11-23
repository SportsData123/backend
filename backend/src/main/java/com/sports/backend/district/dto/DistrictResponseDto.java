package com.sports.backend.district.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DistrictResponseDto {
    private int districtId;
    private String districtCode;
    private String districtName;
}
