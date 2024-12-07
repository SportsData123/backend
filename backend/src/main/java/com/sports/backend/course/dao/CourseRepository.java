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
            "WHERE (:isAccessibleForDisabled IS NULL OR c.isAccessibleForDisabled = :isAccessibleForDisabled) " +
            "AND (:weekday IS NULL OR c.weekday LIKE %:weekday%) " +
            "AND (:sportName IS NULL OR c.sport.sportName = :sportName) " +
            "AND (:startTime IS NULL OR c.startTime >= :startTime) " +
            "AND (:endTime IS NULL OR c.endTime <= :endTime)")
    Page<Course> findFiltered(@Param("isAccessibleForDisabled") Boolean isAccessibleForDisabled,
                              @Param("weekday") String weekday,
                              @Param("sportName") String sportName,
                              @Param("startTime") LocalTime startTime,
                              @Param("endTime") LocalTime endTime,
                              Pageable pageable);
}
