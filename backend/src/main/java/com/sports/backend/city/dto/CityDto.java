package com.sports.backend.city.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

/**
 * CityDto는 도시 데이터를 전달하기 위한 Data Transfer Object(DTO) 클래스입니다.
 * CSV 파일의 데이터를 Java 객체로 매핑하기 위해 OpenCSV의 @CsvBindByName 어노테이션을 사용합니다.
 */

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