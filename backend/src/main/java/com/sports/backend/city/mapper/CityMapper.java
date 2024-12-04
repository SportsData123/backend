package com.sports.backend.city.mapper;

import com.sports.backend.city.domain.City;
import com.sports.backend.city.dto.CityResponseDto;

public class CityMapper {
    /**
     * City 엔티티를 CityResponseDto로 변환합니다.
     *
     * @param city City 엔티티
     * @return CityResponseDto 변환된 DTO 객체
     */
    public static CityResponseDto toResponseDto(City city) {
        return new CityResponseDto(
                city.getCityId(),
                city.getCityName(),
                city.getCityCode()
        );
    }

    /**
     * CSV 레코드 배열을 City 엔티티로 변환합니다.
     *
     * @param record CSV 레코드 배열
     * @return City 변환된 City 엔티티
     */
    public static City fromCsvRecord(String[] record) {
        String cityCode = record[0].substring(0, 2);        // 법정동코드 (왼쪽 2자리)
        String cityName = record[1].split(" ")[0];         // 법정동명 (첫 단어)
        String exist = record[2];                          // 폐지여부

        if ("존재".equals(exist)) {
            return City.builder()
                    .cityCode(cityCode)
                    .cityName(cityName)
                    .build();
        }
        return null;
    }
}
