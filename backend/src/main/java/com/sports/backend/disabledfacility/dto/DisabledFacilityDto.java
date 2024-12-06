package com.sports.backend.disabledfacility.dto;

import lombok.Builder;
import lombok.Data;

/**
 * `DisabledFacilityDto`는 장애인 시설 데이터를 전송하기 위한 DTO(Data Transfer Object) 클래스입니다.
 * 이 클래스는 엔티티와 클라이언트 간에 데이터를 전달하는 역할을 합니다.
 */
@Data
@Builder
public class DisabledFacilityDto {
    private String facilName;
    private String resTelno;
    private String mainEventName;
    private String cityCode;
    private String cityName;
    private String districtCode;
    private String districtName;
    private String roadAddr;
    private String faciDaddr;
}
