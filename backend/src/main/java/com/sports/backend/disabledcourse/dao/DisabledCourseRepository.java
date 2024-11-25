package com.sports.backend.disabledcourse.dao;

import com.sports.backend.disabledcourse.domain.DisabledCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisabledCourseRepository extends JpaRepository<DisabledCourse, Integer> {
}
