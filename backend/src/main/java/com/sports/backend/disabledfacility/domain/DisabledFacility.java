package com.sports.backend.disabledfacility.domain;

import com.sports.backend.city.domain.City;
import com.sports.backend.district.domain.District;
import com.sports.backend.facility.domain.Facility;
import jakarta.persistence.*;
import lombok.*;

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

    @ManyToOne
    @JoinColumn(name = "city_id", foreignKey = @ForeignKey(name = "fk_disabled_facility_city"))
    private City city;

    @ManyToOne
    @JoinColumn(name = "district_id", foreignKey = @ForeignKey(name = "fk_disabled_facility_district"))
    private District district;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facility_id", nullable = true) // 외래키를 nullable로 설정
    private Facility facility;
}
