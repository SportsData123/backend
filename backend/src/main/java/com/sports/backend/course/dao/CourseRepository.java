package com.sports.backend.course.dao;

import com.sports.backend.course.domain.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findByFacilityId(int facilityId);

    @Query("SELECT c FROM Course c " +
            "WHERE (:isAccessibleForDisabled IS NULL OR UPPER(c.isAccessibleForDisabled) = UPPER(:isAccessibleForDisabled)) " +
            "AND (:sportName IS NULL OR c.sportName = :sportName) " +
            "AND (:startTime IS NULL OR c.startTime >= :startTime) " +
            "AND (:endTime IS NULL OR c.endTime <= :endTime) " +
            "AND (:weekday IS NULL OR " +
            "     (SUBSTRING(:weekday, 1, 1) = '0' OR SUBSTRING(c.weekday, 1, 1) = '1') AND " +
            "     (SUBSTRING(:weekday, 2, 1) = '0' OR SUBSTRING(c.weekday, 2, 1) = '1') AND " +
            "     (SUBSTRING(:weekday, 3, 1) = '0' OR SUBSTRING(c.weekday, 3, 1) = '1') AND " +
            "     (SUBSTRING(:weekday, 4, 1) = '0' OR SUBSTRING(c.weekday, 4, 1) = '1') AND " +
            "     (SUBSTRING(:weekday, 5, 1) = '0' OR SUBSTRING(c.weekday, 5, 1) = '1') AND " +
            "     (SUBSTRING(:weekday, 6, 1) = '0' OR SUBSTRING(c.weekday, 6, 1) = '1') AND " +
            "     (SUBSTRING(:weekday, 7, 1) = '0' OR SUBSTRING(c.weekday, 7, 1) = '1')) " +
            "AND (:cityId IS NULL OR c.cityId = :cityId) " +
            "AND (:districtId IS NULL OR c.districtId = :districtId)")
    Page<Course> findFiltered(@Param("isAccessibleForDisabled") String isAccessibleForDisabled,
                              @Param("weekday") String weekday,
                              @Param("sportName") String sportName,
                              @Param("startTime") LocalTime startTime,
                              @Param("endTime") LocalTime endTime,
                              @Param("cityId") Integer cityId,
                              @Param("districtId") Integer districtId,
                              Pageable pageable);
}
