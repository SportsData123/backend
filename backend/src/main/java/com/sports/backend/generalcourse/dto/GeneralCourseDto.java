package com.sports.backend.generalcourse.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;

/**
 * `GeneralCourseDto`는 일반 강좌 데이터를 전송하기 위한 데이터 전송 객체(Data Transfer Object)입니다.
 * 클라이언트와의 데이터 교환에 사용됩니다.
 */
@Data
@Builder
public class GeneralCourseDto {
    private int generalCourseId;   // 일반 강좌 ID
    private String busiRegNo;      // 사업자 등록 번호
    private String sportNm;        // 종목 이름
    private String courseNm;       // 강좌 이름
    private LocalTime startTime;   // 시작 시간
    private LocalTime endTime;     // 끝나는 시간
    private String weekday;        // 요일
    private String courseSetaDesc; // 강좌 상세 설명
    private String facilSn;        // 시설 일련 번호
    private String settlAmt;       // 요금
    private String roadAddr;       // 도로명 주소
    private String faciDaddr;      // 시설 상세 주소
    private String districtName;   // 구 이름
    private String cityName;       // 도시 이름
    private Double latitude;       // 위도
    private Double longitude;      // 경도
}
