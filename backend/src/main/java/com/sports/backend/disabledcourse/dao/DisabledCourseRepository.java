package com.sports.backend.disabledcourse.dao;

import com.sports.backend.disabledcourse.domain.DisabledCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface DisabledCourseRepository extends JpaRepository<DisabledCourse, Integer> {
    @Query("SELECT dc FROM DisabledCourse dc WHERE dc.facilityId = :facilityId")
    List<DisabledCourse> findByFacilityId(@Param("facilityId") int facilityId);
}
