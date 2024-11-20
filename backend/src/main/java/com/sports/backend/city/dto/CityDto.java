package com.sports.backend.city.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityDto {
    @CsvBindByName(column = "법정동코드")
    private String cityCode;

    @CsvBindByName(column = "법정동명")
    private String cityName;

    @CsvBindByName(column = "폐지여부")
    private String exist;

    @Override
    public String toString() {
        return "CityDto{" +
                "cityCode='" + cityCode + '\'' +
                ", cityName='" + cityName + '\'' +
                ", 존재여부='" + exist + '\'' +
                '}';
    }
}