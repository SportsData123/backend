package com.sports.backend.facility.dao;

import com.sports.backend.facility.domain.Facility;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FacilityRepository extends JpaRepository<Facility, Long> {
    @Query("SELECT f FROM Facility f WHERE " +
            "(:cityId IS NULL OR f.city.cityId = :cityId) AND " +
            "(:districtId IS NULL OR f.district.districtId = :districtId) AND " +
            "(:isAccessibleForDisabled IS NULL OR COALESCE(f.isAccessibleForDisabled, 'N') = :isAccessibleForDisabled)")
    List<Facility> findAllWithFilters(String cityId, String districtId, String isAccessibleForDisabled, Pageable pageable);
}