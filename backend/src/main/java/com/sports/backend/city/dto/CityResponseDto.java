package com.sports.backend.city.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CityResponseDto {
    private int cityId;
    private String cityName;
    private String cityCode;
}