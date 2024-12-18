package com.sports.backend.generalfacility.dao;

import com.sports.backend.generalfacility.domain.GeneralFacility;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GeneralFacilityRepository extends JpaRepository<GeneralFacility, Integer>{
    @Query("SELECT gf FROM GeneralFacility gf WHERE " +
            "(:cityId IS NULL OR gf.cityCode = :cityId) AND " +
            "(:districtId IS NULL OR gf.districtCode = :districtId) AND " +
            "(:isAccessibleForDisabled IS NULL OR COALESCE(gf.isAccessibleForDisabled, 'N') = :isAccessibleForDisabled)")
    List<GeneralFacility> findWithFilters(String cityId, String districtId, String isAccessibleForDisabled, Pageable pageable);
}
