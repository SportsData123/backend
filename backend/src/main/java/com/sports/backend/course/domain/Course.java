package com.sports.backend.course.domain;

import com.sports.backend.sport.domain.Sport;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private int courseId; // 강좌 ID

    @Column(name = "business_registration_no", length = 20)
    private String busiRegNo; // 사업자 등록번호

    @Column(name = "sport_name", length = 20)
    private String sportName; // 종목명

    @Column(name = "course_name", length = 100)
    private String courseName; // 강좌명

    @Column(name = "start_time")
    private LocalTime startTime; // 시작 시간

    @Column(name = "end_time")
    private LocalTime endTime; // 종료 시간

    @Column(name = "weekday", length = 7)
    private String weekday; // 요일 구분 값 (예: 1010100 -> 월수금)

    @Column(name = "course_desc", columnDefinition = "TEXT")
    private String courseDesc; // 강좌 상세 설명

    @Column(name = "is_accessible_for_disabled", length = 1)
    private String isAccessibleForDisabled; // 장애인 접근 가능 여부 (Y/N)

    @Column(name = "facil_sn", length = 20)
    private String facilSn; // 시설 시리얼 넘버

    @Column(name = "settl_amt")
    private String settlAmt; // 결제 금액

    @Column(name = "road_addr", length = 255)
    private String roadAddr; // 도로명 주소

    @Column(name = "faci_daddr", length = 255)
    private String faciDaddr; // 상세 주소

    @Column(name = "district_name", length = 50)
    private String districtName; // 구 이름

    @Column(name = "district_code", length = 3)
    private String districtCode; // 구 코드

    @Column(name = "district_id")
    private Integer districtId; // 구 ID

    @Column(name = "city_name", length = 10)
    private String cityName; // 도시 이름

    @Column(name = "city_code", length = 2)
    private String cityCode; // 도시 코드

    @Column(name = "city_id")
    private Integer cityId; // 도시 ID

    @Column(name = "latitude", precision = 9)
    private Double latitude; // 위도

    @Column(name = "longitude", precision = 9)
    private Double longitude; // 경도

    @Column(name = "facility_id")
    private Integer facilityId; // 시설 ID
}
