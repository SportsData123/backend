package com.sports.backend.disabledfacility.dao;

import com.sports.backend.disabledfacility.domain.DisabledFacility;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DisabledFacilityRepository extends JpaRepository<DisabledFacility, Integer>{
    @Query("SELECT df FROM DisabledFacility df WHERE " +
            "(:cityId IS NULL AND :districtId IS NULL) OR " +
            "(:cityId IS NULL OR df.cityCode = :cityId) AND " +
            "(:districtId IS NULL OR df.districtCode = :districtId)")
    List<DisabledFacility> findWithFilters(String cityId, String districtId, Pageable pageable);
}
