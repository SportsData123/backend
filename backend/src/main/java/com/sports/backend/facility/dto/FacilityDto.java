package com.sports.backend.facility.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FacilityDto {
    private String facilityName;    // 시설명
    private String facilityType;    // 시설 유형
    private String facilityStatus;  // 시설 상태
    private String roadAddress;     // 도로명 주소
    private String detailAddress;   // 상세 주소
    private String zipCode;         // 우편번호
    private Double longitude;       // 경도
    private Double latitude;        // 위도
    private String inOutType;       // 실내외 구분
    private String nationFlag;      // 국가 체육시설 여부
    private String cityName;        // 시도명
    private String districtName;    // 시군구명
}