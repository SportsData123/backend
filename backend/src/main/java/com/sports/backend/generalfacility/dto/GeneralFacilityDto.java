package com.sports.backend.generalfacility.dto;

import lombok.Builder;
import lombok.Data;

/**
 * `GeneralFacilityDto`는 일반 시설 데이터를 전송하기 위한 DTO(Data Transfer Object) 클래스입니다.
 * 엔터티와 클라이언트 간 데이터를 주고받는 데 사용됩니다.
 */
@Data
@Builder
public class GeneralFacilityDto {
    private String facilName;     // 시설 이름
    private String resTelno;      // 시설 전화번호
    private String mainEventName; // 종목 이름
    private String cityCode;      // 도시 코드
    private String cityName;      // 도시 이름
    private String districtCode;  // 지역 코드
    private String districtName;  // 지역 이름
    private String brno;          // 사업자 등록 번호
    private String facilSn;       // 시설 일련 번호
    private String roadAddr;      // 도로명 주소
    private String faciDaddr;     // 상세 주소
}
