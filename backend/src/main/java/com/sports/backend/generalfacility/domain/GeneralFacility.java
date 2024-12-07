package com.sports.backend.generalfacility.domain;

import com.sports.backend.city.domain.City;
import com.sports.backend.district.domain.District;
import com.sports.backend.facility.domain.Facility;
import jakarta.persistence.*;
import lombok.*;

/**
 * `GeneralFacility`는 일반 시설 데이터를 나타내는 엔터티 클래스입니다.
 * 데이터베이스 테이블 `general_facility`와 매핑되며, 일반 시설의 속성과 관련된 정보를 포함합니다.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "general_facility")
public class GeneralFacility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "general_facility_id")
    private int generalFacilityId;

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

    @Column(name = "brno", length = 20)
    private String brno;

    @Column(name = "facil_sn", length = 20)
    private String facilSn;

    @Column(name = "is_accessible_for_disabled", length = 1)
    private String isAccessibleForDisabled;

    @Column(name = "road_addr", length = 255)
    private String roadAddr;

    @Column(name = "faci_daddr", length = 255)
    private String faciDaddr;

    /**
     * 도시와의 관계
     * `City` 엔터티와 다대일 관계를 설정하며 외래 키는 `city_id`입니다.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", foreignKey = @ForeignKey(name = "fk_general_facility_city"))
    private City city;

    /**
     * 구와의 관계
     * `District` 엔터티와 다대일 관계를 설정하며 외래 키는 `district_id`입니다.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id", foreignKey = @ForeignKey(name = "fk_general_facility_district"))
    private District district;

    /**
     * 시설과의 관계
     * `Facility` 엔터티와 일대일 관계를 설정하며 외래 키는 `facility_id`입니다.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facility_id", referencedColumnName = "facility_id")
    private Facility facility;
}
