package com.sports.backend.facility.dao;

import com.sports.backend.facility.domain.Facility;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// JPA Repository
public interface FacilityRepository extends JpaRepository<Facility, Long> {
    @Query("SELECT f FROM Facility f " +
            "WHERE f.city.cityCode = :cityCode " +
            "AND f.district.districtCode = :districtCode " +
            "AND (:isAccessibleForDisabled IS NULL OR f.facilityName.isAccessibleForDisabled = :isAccessibleForDisabled)")
    Page<Facility> findFacilities(
            String cityCode,
            String districtCode,
            char isAccessibleForDisabled,
            Pageable pageable
    );
}