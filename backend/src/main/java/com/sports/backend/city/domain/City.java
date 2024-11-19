package com.sports.backend.city.domain;

import com.sports.backend.district.domain.District;
import com.sports.backend.facility.domain.Facility;
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
    private Long cityId; // 도시 ID (자동 증가)

    @Column(nullable = false, length = 10, unique = true)
    private String cityName; // 도시명

    @Column(nullable = false, length = 2, unique = true)
    private String cityCode; // 도시 코드

    @OneToMany(mappedBy = "city", cascade = CascadeType.REMOVE)
    private List<District> districts; // 도시와 연결된 지역 목록

    @OneToMany(mappedBy = "city", cascade = CascadeType.REMOVE)
    private List<Facility> facilities; // 도시와 연결된 시설 목록
}
