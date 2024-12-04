package com.sports.backend.city.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * CityResponseDto는 도시 정보를 클라이언트에 반환하기 위한 Data Transfer Object(DTO)입니다.
 * - 도시 ID, 도시 이름, 도시 코드를 포함합니다.
 * - 읽기 전용 속성을 제공하기 위해 @Getter를 사용했습니다.
 */

@Getter
@AllArgsConstructor
public class CityResponseDto {
    private int cityId;
    private String cityName;
    private String cityCode;
}