package com.sports.backend.facility.domain;

import com.sports.backend.city.domain.City;
import com.sports.backend.district.domain.District;
import com.sports.backend.facilityBusiness.domain.FacilityBusiness;
import jakarta.persistence.*;
import lombok.*;


// Facility Entity 클래스
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "facility")
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int facilityId;

    @Column(length = 10)
    private String facilityType;

    @Column(length = 10)
    private String facilityStatus;

    @Column(length = 100)
    private String roadAddress;

    @Column(length = 200)
    private String detailAddress;

    @Column(length = 10)
    private String zipCode;

    @Column
    private double longitude;

    @Column
    private double latitude;

    @Column(length = 10)
    private String inOutType;

    @Column(length = 1)
    private String nationFlag;

    @ManyToOne
    @JoinColumn(name = "city_name", referencedColumnName = "cityName", nullable = false)
    private City city;

    @ManyToOne
    @JoinColumn(name = "district_name", referencedColumnName = "districtName", nullable = false)
    private District district;

    @OneToOne
    @JoinColumn(name = "facility_name", referencedColumnName = "facilityName", nullable = false)
    private FacilityBusiness facilityName;
}
