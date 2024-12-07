package com.sports.backend.city.domain;

import com.sports.backend.district.domain.District;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * City 엔티티는 도시 정보를 나타내며, 데이터베이스의 "city" 테이블과 매핑됩니다.
 * 각 도시에는 ID, 이름, 코드가 포함되며, 관련된 지역(District)과의 관계가 정의됩니다.
 */

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

    /**
     * 도시와 연결된 지역 목록 (1:N 관계)
     * - City는 여러 District를 가질 수 있음.
     * - District의 "city" 필드와 매핑됨.
     * - CascadeType.ALL 설정으로 City와 관련된 District에 대해 일괄 처리 가능.
     */
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<District> districts; // 도시와 연결된 지역 목록

}