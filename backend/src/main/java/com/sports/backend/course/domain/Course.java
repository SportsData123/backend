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
    private int courseId;

    @Column(name = "course_name", length = 100, nullable = false)
    private String courseName; // 강좌명

    @Column(name = "course_desc", columnDefinition = "TEXT")
    private String courseSetaDesc; // 강좌 상세 설명

    @Column(name = "start_time")
    private LocalTime startTime; // 시작 시간

    @Column(name = "end_time")
    private LocalTime endTime; // 종료 시간

    @Column(name = "weekday", length = 7)
    private String weekday; // 요일 구분 값 (1010100 -> 월 수 금)

    @Column(name = "fee")
    private String settlAmt; // 결제 금액

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

    @Column(name = "facil_sn", length = 20)
    private String facilSn; // 시설 고유번호 (GeneralCourse에만 존재)

    @Column(name = "is_accessible_for_disabled", length = 1)
    private String isAccessibleForDisabled; // 장애인 접근 가능 여부 (DisabledCourse에만 존재)

    @Column(name = "business_registration_no", length = 20)
    private String busiRegNo; // 사업자 등록번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sport_id")
    private Sport sport; // 스포츠 연관 관계
}
