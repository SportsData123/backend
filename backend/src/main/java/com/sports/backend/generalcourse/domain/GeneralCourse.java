package com.sports.backend.generalcourse.domain;

import com.sports.backend.facility.domain.Facility;
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
@Table(name = "general_course")
public class GeneralCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "general_course_id")
    private int generalCourseId;

    @Column (name = "business_registration_no", length = 20)
    private String busiRegNo; // 사업자 등록번호

    @Column (name = "sport_name", length = 20)
    private String sportNm; // 종목명

    @Column (name = "course_name", length = 100)
    private String courseNm; // 강좌명

    @Column (name = "start_time")
    private LocalTime startTime; // 시작 시간

    @Column (name = "end_time")
    private LocalTime endTime; // 종료 시간

    @Column (name = "weekday", length = 7)
    private String weekday; // 요일 구분 값 (1010100 -> 월 수 금)

    @Column (name = "course_desc", columnDefinition = "TEXT")
    private String courseSetaDesc; // 강좌 상세 설명

    @Column (name = "is_accessible_for_disabled")
    private String isAccessibleForDisabled;

    @Column (name = "facil_sn", length = 20)
    private String facilSn;

    @Column (name = "settl_amt")
    private String settlAmt;

    @Column(name = "road_addr", length = 255)
    private String roadAddr;

    @Column(name = "faci_daddr", length = 255)
    private String faciDaddr;

    @Column(name = "district_name", length = 50)
    private String districtName;

    @Column(name = "city_name", length = 10)
    private String cityName;

    @Column(name = "latitude", precision = 9)
    private Double latitude;

    @Column(name = "longitude", precision = 9)
    private Double longitude;

    @Column(name = "facility_id")
    private Integer facilityId;

    //@ManyToOne
    //@JoinColumn(name = "sport_id", nullable = false)
    //private Sport sport;
}
