package com.sports.backend.course.controller;

import com.sports.backend.common.ApiResponse;
import com.sports.backend.course.dto.PaginatedCourseResponseDto;
import com.sports.backend.course.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * CourseControllerTest는 CourseController 클래스의 동작을 테스트하는 단위 테스트 클래스입니다.
 * 주로 getFilteredCourses 메서드의 동작을 검증합니다.
 */
public class CourseControllerTest {

    @Mock
    private CourseService courseService;

    @InjectMocks
    private CourseController courseController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Mock 객체 초기화
    }

    /**
     * getFilteredCourses 메서드가 정상적으로 동작하는지 테스트합니다.
     * - 필터 조건이 없는 상태에서 페이지네이션 강좌 목록을 반환하는지 확인.
     */
    @Test
    void getFilteredCourses_ReturnsCourses() {
        // Given
        PaginatedCourseResponseDto paginatedCourses = PaginatedCourseResponseDto.builder()
                .totalCount(1)
                .page(1)
                .size(10)
                .totalPages(1)
                .courses(Collections.emptyList())
                .build();

        when(courseService.getAllCourses(0, 10, null, null, null, null, null)).thenReturn(paginatedCourses);

        // When
        ResponseEntity<ApiResponse<PaginatedCourseResponseDto>> response = courseController.getFilteredCourses(
                null, null, null, null, null, 1, 10);

        // Then
        assertNotNull(response);
        assertEquals(200, response.getBody().getStatus());
        assertEquals(1, response.getBody().getData().getTotalCount());
    }

}
