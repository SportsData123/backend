package com.sports.backend.generalcourse.dao;

import com.sports.backend.generalcourse.domain.GeneralCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;
import java.util.List;

/**
 * `GeneralCourseRepository`는 일반 강좌(GeneralCourse) 데이터를 관리하는 JPA 리포지토리 인터페이스입니다.
 * 데이터베이스와의 상호작용을 처리하며, 표준 JPA 메서드 외에 커스텀 쿼리 메서드를 제공합니다.
 */
public interface GeneralCourseRepository extends JpaRepository<GeneralCourse, Integer> {

    /**
     * 특정 시설 ID에 해당하는 일반 강좌 데이터를 조회하는 메서드입니다.
     *
     * @param facilityId 조회할 시설의 ID
     * @return 해당 시설 ID에 속한 일반 강좌 데이터 목록
     */
    @Query("SELECT gc FROM GeneralCourse gc WHERE gc.facilityId = :facilityId")
    List<GeneralCourse> findByFacilityId(@Param("facilityId") int facilityId);


    @Query("SELECT g FROM GeneralCourse g " +
            "WHERE (:weekday IS NULL OR FUNCTION('matchesWeekday', g.weekday, :weekday) = true) " +
            "AND (:sportName IS NULL OR LOWER(g.sportNm) = LOWER(:sportName)) " +
            "AND (:startTime IS NULL OR g.startTime >= :startTime) " +
            "AND (:endTime IS NULL OR g.endTime <= :endTime)")
    List<GeneralCourse> findFiltered(
            @Param("weekday") String weekday,
            @Param("sportName") String sportName,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime
    );
}
