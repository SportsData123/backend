package com.sports.backend.facilityBusiness.domain;

import com.sports.backend.facility.domain.Facility;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "facility_business")
public class FacilityBusiness {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int businessId;

    @Column(nullable = false, length = 20, unique = true)
    private String businessRegistrationNo;

    @Column(length = 20)
    private String contactNumber;

    @Column(length = 20)
    private String facilitySerialNo;

    @Column(length = 50)
    private String facilityName;

    @Column(length = 1)
    private char isAccessibleForDisabled; // Y/N 값

    @OneToOne(mappedBy = "facilityName", cascade = CascadeType.REMOVE)
    private Facility facility;
}
