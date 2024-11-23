package com.sports.backend.city.domain;

import com.sports.backend.district.domain.District;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "city")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private int cityId; // 도시 ID (자동 증가)

    @Column(name = "city_name", nullable = false, length = 10, unique = true)
    private String cityName; // 도시명

    @Column(name = "city_code", nullable = false, length = 2, unique = true)
    private String cityCode; // 도시 코드

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<District> districts; // 도시와 연결된 지역 목록
}