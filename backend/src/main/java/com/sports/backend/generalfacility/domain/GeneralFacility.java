package com.sports.backend.generalfacility.domain;

import com.sports.backend.city.domain.City;
import com.sports.backend.facility.domain.Facility;
import jakarta.persistence.*;
import lombok.*;

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

    @ManyToOne // City와 관계 설정
    @JoinColumn(name = "city_id", foreignKey = @ForeignKey(name = "fk_general_facility_city"))
    private City city; // City 엔터티와 연결

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facility_id", referencedColumnName = "facility_id")
    private Facility facility;
}
