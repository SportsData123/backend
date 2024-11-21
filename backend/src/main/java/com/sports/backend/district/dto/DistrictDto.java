package com.sports.backend.district.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DistrictDto {
    @CsvBindByName(column = "법정동코드")
    private String districtCode;

    @CsvBindByName(column = "법정동명")
    private String districtName;

    @CsvBindByName(column = "폐지여부")
    private String exist;

    @Override
    public String toString() {
        return "DistrictDto{" +
                "districtCode='" + districtCode + '\'' +
                ", districtName='" + districtName + '\'' +
                ", exist='" + exist + '\'' +
                '}';
    }
}
