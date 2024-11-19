package com.sports.backend.facility.domain;

import com.sports.backend.city.domain.City;
import com.sports.backend.district.domain.District;
import jakarta.persistence.*;
import lombok.*;


// Facility Entity 클래스
@Entity
@Getter
@Setter
@Table(name = "facility")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long facilityId;

    @Column(nullable = false, length = 10)
    private String facilityName;

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

    @Column(precision = 9, scale = 6)
    private Double longitude;

    @Column(precision = 9, scale = 6)
    private Double latitude;

    @Column(length = 10)
    private String inOutType;

    @Column(length = 1)
    private String nationFlag;

    @ManyToOne
    @JoinColumn(name = "city_name", referencedColumnName = "cityName")
    private City city;

    @ManyToOne
    @JoinColumn(name = "district_name", referencedColumnName = "districtName")
    private District district;
}
