package com.sports.backend.disabledcourse.domain;

import com.sports.backend.city.domain.City;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "disabled_course")
public class DisabledCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "disabled_course_id")
    private int disabledCourseId;

    @Column (name = "business_registration_no", length = 20)
    private String busiRegNo; // 사업자 등록번호

    @Column (name = "sport_name", length = 20)
    private String sportNm; // 종목명

    @Column (name = "course_name", length = 100)
    private String courseNm; // 강좌명

    @Column (name = "start_time")
    private String startTime; // 시작 시간

    @Column (name = "end_time")
    private String endTime; // 종료 시간

    @Column (name = "weekday", length = 7)
    private String weekday; // 요일 구분 값 (1010100 -> 월 수 금)

    @Column (name = "course_desc")
    private String courseSetaDesc; // 강좌 상세 설명

    @Column (name = "fee")
    private String settlAmt; // 결제 금액
}
