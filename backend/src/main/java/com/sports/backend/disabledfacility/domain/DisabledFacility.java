package com.sports.backend.disabledfacility.domain;

import com.sports.backend.city.domain.City;
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

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "city_id")
    private City city;
}
