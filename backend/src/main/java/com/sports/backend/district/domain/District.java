package com.sports.backend.district.domain;

import com.sports.backend.city.domain.City;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "district")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "district_id")
    private int districtId; // 지역 ID (자동 증가)

    @Column(name = "district_code", nullable = false, length = 3, unique = true)
    private String districtCode; // 법정동코드 중 3~5번째 자리

    @Column(name = "district_name", nullable = false, length = 50)
    private String districtName; // 법정동명 중 두 번째 단어

    @Column(name = "city_code", nullable = false, length = 2)
    private String cityCode;
}
