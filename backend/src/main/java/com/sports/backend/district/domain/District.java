package com.sports.backend.district.domain;

import com.sports.backend.city.domain.City;
import com.sports.backend.facility.domain.Facility;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "district")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long districtId; // 지역 ID (자동 증가)

    @Column(nullable = false, length = 20, unique = true)
    private String districtName; // 지역명

    @Column(nullable = false, length = 3, unique = true)
    private String districtCode; // 지역 코드

    @ManyToOne
    @JoinColumn(name = "city_name", referencedColumnName = "city_name", nullable = false)
    private City city; // 지역이 속한 도시

    @OneToMany(mappedBy = "district", cascade = CascadeType.REMOVE)
    private List<Facility> facilities; // 지역과 연결된 시설 목록
}