package com.sports.backend.disabledfacility.domain;

import com.sports.backend.city.domain.City;
import com.sports.backend.district.domain.District;
import com.sports.backend.facility.domain.Facility;
import jakarta.persistence.*;
import lombok.*;

/**
 * `DisabledFacility`는 장애인 시설 데이터를 나타내는 엔티티 클래스입니다.
 * 이 클래스는 데이터베이스의 `disabled_facility` 테이블과 매핑됩니다.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "disabled_facility")
public class DisabledFacility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "disabled_facility_id")
    private int disabledFacilityId;

    @Column(name = "facil_name", nullable = false, length = 100)
    private String facilName;

    @Column(name = "res_telno", length = 20)
    private String resTelno;

    @Column(name = "main_event_name", length = 100)
    private String mainEventName;

    @Column(name = "city_code", length = 2)
    private String cityCode;

    @Column(name = "city_name", length = 10)
    private String cityName;

    @Column(name = "district_code", length = 3)
    private String districtCode;

    @Column(name = "district_name", length = 50)
    private String districtName;

    @Column(name = "is_accessible_for_disabled", length = 1)
    private String isAccessibleForDisabled;

    @Column(name = "road_addr", length = 200)
    private String roadAddr;

    @Column(name = "faci_daddr", length = 200)
    private String faciDaddr;

    /**
     * 도시 정보와 연관된 외래 키
     * - 참조: `City` 엔티티
     * - 외래 키 이름: `fk_disabled_facility_city`
     */
    @ManyToOne
    @JoinColumn(name = "city_id", foreignKey = @ForeignKey(name = "fk_disabled_facility_city"))
    private City city;

    /**
     * 구 정보와 연관된 외래 키
     * - 참조: `District` 엔티티
     * - 외래 키 이름: `fk_disabled_facility_district`
     */
    @ManyToOne
    @JoinColumn(name = "district_id", foreignKey = @ForeignKey(name = "fk_disabled_facility_district"))
    private District district;

    /**
     * 시설 정보와 연관된 외래 키
     * - 참조: `Facility` 엔티티
     * - 외래 키 이름: `facility_id`
     * - Fetch 전략: LAZY
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facility_id", nullable = true) // 외래키를 nullable로 설정
    private Facility facility;
}
