package com.sports.backend.course.service;

import com.sports.backend.course.dao.CourseRepository;
import com.sports.backend.course.domain.Course;
import com.sports.backend.course.dto.CourseResponseDto;
import com.sports.backend.course.dto.PaginatedCourseResponseDto;
import com.sports.backend.course.mapper.CourseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * CourseService는 강좌 데이터를 처리하는 서비스 클래스입니다.
 * - 장애인 강좌 및 일반 강좌 데이터를 조회 및 필터링합니다.
 * - 강좌 데이터를 병합하고, 페이징 처리하여 반환합니다.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CourseService {

    private final CourseRepository courseRepository;


    /**
     * 모든 강좌 데이터를 조회하고, 필터링 조건을 적용한 후 페이징 처리하여 반환합니다.
     *
     * @param page 페이지 번호 (0-based)
     * @param size 페이지 크기
     * @param isAccessibleForDisabled 장애인 접근 가능 여부 필터 (optional)
     * @param weekday 요일 필터 (optional)
     * @param sportName 스포츠 종목 필터 (optional)
     * @param startTime 강좌 시작 시간 필터 (optional)
     * @param endTime 강좌 종료 시간 필터 (optional)
     * @param cityId 시/도 ID 필터 (optional)
     * @param districtId 시/군/구 ID 필터 (optional)
     * @return PaginatedCourseResponseDto 페이징 처리된 강좌 데이터와 메타 정보
     */
    @Transactional(readOnly = true)
    public PaginatedCourseResponseDto getAllCourses(int page, int size,
                                                    String isAccessibleForDisabled,
                                                    String weekday,
                                                    String sportName,
                                                    String startTime,
                                                    String endTime,
                                                    Integer cityId,
                                                    Integer districtId) {

        Pageable pageable = PageRequest.of(page, size);

        // 필터링된 강좌 데이터 조회
        Page<Course> coursePage = courseRepository.findFiltered(
                isAccessibleForDisabled,
                weekday,
                sportName,
                startTime != null ? LocalTime.parse(startTime) : null,
                endTime != null ? LocalTime.parse(endTime) : null,
                cityId,
                districtId,
                pageable
        );

        // 엔티티를 DTO로 변환
        List<CourseResponseDto> courses = coursePage.getContent().stream()
                .map(CourseMapper::toDto)
                .toList();

        // 페이징 정보 포함 응답 생성
        return PaginatedCourseResponseDto.builder()
                .totalCount(coursePage.getTotalElements()) // 총 강좌 수
                .page(page + 1)                            // 요청 페이지 0-based에서 1-based로 변환
                .size(size)                                // 페이지 크기
                .totalPages(coursePage.getTotalPages())    // 총 페이지 수
                .courses(courses)                         // 현재 페이지 데이터
                .build();
    }
}
