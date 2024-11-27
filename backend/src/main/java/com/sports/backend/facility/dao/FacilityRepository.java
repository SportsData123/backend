package com.sports.backend.facility.dao;

import com.sports.backend.facility.domain.Facility;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FacilityRepository extends JpaRepository<Facility, Long> {
    @Query("SELECT f FROM Facility f LEFT JOIN FETCH f.city c LEFT JOIN FETCH f.district d " +
            "WHERE (:cityId IS NULL AND :districtId IS NULL AND :isAccessibleForDisabled IS NULL) OR " +
            "(:cityId IS NULL OR c.cityId = :cityId) AND " +
            "(:districtId IS NULL OR d.districtId = :districtId) AND " +
            "(:isAccessibleForDisabled IS NULL OR f.isAccessibleForDisabled = :isAccessibleForDisabled)")
    List<Facility> findAllWithFilters(String cityId, String districtId, String isAccessibleForDisabled, Pageable pageable);
}