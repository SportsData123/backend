package com.sports.backend.facility.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FacilityRequestDto {
    private String cityCode; // 도시 법정동 코드
    private String districtCode; // 시군구 법정동 코드
    private char isAccessibleForDisabled; // 장애인 이용 가능 시설 여부
    private int page; // 요청 페이지 번호
    private int size; // 한 페이지당 결과 수
}
