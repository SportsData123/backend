package com.sports.backend.generalcourse.dao;

import com.sports.backend.generalcourse.domain.GeneralCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneralCourseRepository extends JpaRepository<GeneralCourse, Integer> {
}
