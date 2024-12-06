package com.sports.backend.facility.dao;

import com.sports.backend.facility.domain.Facility;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FacilityRepository extends JpaRepository<Facility, Long> {
    @Query("SELECT f FROM Facility f " +
            "LEFT JOIN FETCH f.generalFacility gf " +
            "LEFT JOIN FETCH f.disabledFacility df " +
            "WHERE (:cityId IS NULL OR f.city.cityId = :cityId) " +
            "AND (:districtId IS NULL OR f.district.districtId = :districtId) " +
            "AND (:isAccessibleForDisabled IS NULL OR COALESCE(f.isAccessibleForDisabled, 'N') = :isAccessibleForDisabled)")
    List<Facility> findAllWithFilters(String cityId, String districtId, String isAccessibleForDisabled, Pageable pageable);


    @Query(value =
            "SELECT COUNT(DISTINCT CONCAT(temp.facility_name, '_', temp.city_id, '_', temp.district_id)) " +
                    "FROM ( " +
                    "    SELECT f.facility_name AS facility_name, f.city_id AS city_id, f.district_id AS district_id " +
                    "    FROM facility f " +
                    "    WHERE (:cityId IS NULL OR f.city_id = :cityId) " +
                    "      AND (:districtId IS NULL OR f.district_id = :districtId) " +
                    "      AND (:isAccessibleForDisabled IS NULL OR COALESCE(f.is_accessible_for_disabled, 'N') = :isAccessibleForDisabled) " +
                    "    UNION ALL " +
                    "    SELECT gf.facil_name AS facility_name, gf.city_id AS city_id, gf.district_id AS district_id " +
                    "    FROM general_facility gf " +
                    "    WHERE (:cityId IS NULL OR gf.city_id = :cityId) " +
                    "      AND (:districtId IS NULL OR gf.district_id = :districtId) " +
                    "      AND (:isAccessibleForDisabled IS NULL OR COALESCE(gf.is_accessible_for_disabled, 'N') = :isAccessibleForDisabled) " +
                    "    UNION ALL " +
                    "    SELECT df.facil_name AS facility_name, df.city_id AS city_id, df.district_id AS district_id " +
                    "    FROM disabled_facility df " +
                    "    WHERE (:cityId IS NULL OR df.city_id = :cityId) " +
                    "      AND (:districtId IS NULL OR df.district_id = :districtId) " +
                    "      AND (:isAccessibleForDisabled IS NULL OR COALESCE(df.is_accessible_for_disabled, 'N') = :isAccessibleForDisabled) " +
                    ") temp",
            nativeQuery = true)
    long countUnifiedByFilters(@Param("cityId") String cityId,
                               @Param("districtId") String districtId,
                               @Param("isAccessibleForDisabled") String isAccessibleForDisabled);

}