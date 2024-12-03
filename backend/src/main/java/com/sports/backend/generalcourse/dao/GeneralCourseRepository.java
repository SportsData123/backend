package com.sports.backend.generalcourse.dao;

import com.sports.backend.generalcourse.domain.GeneralCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GeneralCourseRepository extends JpaRepository<GeneralCourse, Integer> {
    @Query("SELECT gc FROM GeneralCourse gc WHERE gc.facilityId = :facilityId")
    List<GeneralCourse> findByFacilityId(@Param("facilityId") int facilityId);
}
