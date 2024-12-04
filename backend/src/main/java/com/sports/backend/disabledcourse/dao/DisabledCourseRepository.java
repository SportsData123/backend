package com.sports.backend.disabledcourse.dao;

import com.sports.backend.disabledcourse.domain.DisabledCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * DisabledCourseRepository는 장애인 강좌 데이터에 대한 데이터 액세스 계층을 제공합니다.
 * Spring Data JPA를 활용하여 데이터베이스와 상호작용합니다.
 */
public interface DisabledCourseRepository extends JpaRepository<DisabledCourse, Integer> {

    /**
     * 특정 시설 ID에 해당하는 장애인 강좌 목록을 조회합니다.
     *
     * @param facilityId 시설 ID
     * @return List<DisabledCourse> 해당 시설 ID와 연결된 장애인 강좌 목록
     */
    @Query("SELECT dc FROM DisabledCourse dc WHERE dc.facilityId = :facilityId")
    List<DisabledCourse> findByFacilityId(@Param("facilityId") int facilityId);

}
