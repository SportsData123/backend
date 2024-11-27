package com.sports.backend.facility.domain;

import com.sports.backend.city.domain.City;
import com.sports.backend.district.domain.District;
import jakarta.persistence.*;
import lombok.*;

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
    @Column(name = "facility_id")
    private int facilityId;

    @Column(name = "facility_name", nullable = false, length = 100)
    private String facilityName;

    @Column(name = "facility_type", length = 50)
    private String facilityType;

    @Column(name = "facility_status", length = 50)
    private String facilityStatus;

    @Column(name = "road_address", length = 200)
    private String roadAddress;

    @Column(name = "detail_address", length = 200)
    private String detailAddress;

    @Column(name = "zip_code", length = 10)
    private String zipCode;

    @Column(name = "longitude", precision = 9)
    private Double longitude;

    @Column(name = "latitude", precision = 9)
    private Double latitude;

    @Column(name = "in_out_type", length = 20)
    private String inOutType;

    @Column(name = "nation_flag", length = 1)
    private String nationFlag;

    @Column(name = "city_name", length = 50)
    private String cityName;

    @Column(name = "district_name", length = 50)
    private String districtName;


    @Column(name = "is_accessible_for_disabled", length = 1)
    private String isAccessibleForDisabled;

    @ManyToOne(fetch = FetchType.LAZY) // Lazy loading for optimization
    @JoinColumn(name = "city_id") // Foreign key column in Facility table
    private City city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id")
    private District district;

}
