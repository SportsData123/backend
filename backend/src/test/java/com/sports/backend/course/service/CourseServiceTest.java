package com.sports.backend.course.service;

import com.sports.backend.course.dto.PaginatedCourseResponseDto;
import com.sports.backend.disabledcourse.dao.DisabledCourseRepository;
import com.sports.backend.generalcourse.dao.GeneralCourseRepository;
import com.sports.backend.disabledcourse.domain.DisabledCourse;
import com.sports.backend.generalcourse.domain.GeneralCourse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * CourseServiceTest는 CourseService의 기능을 단위 테스트하기 위한 클래스입니다.
 * Mock 객체를 사용하여 데이터베이스와의 의존성을 제거하고 서비스 로직의 정확성을 검증합니다.
 */
public class CourseServiceTest {

    @Mock
    private DisabledCourseRepository disabledCourseRepository;

    @Mock
    private GeneralCourseRepository generalCourseRepository;

    @InjectMocks
    private CourseService courseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * getAllCourses 메서드의 동작을 검증합니다.
     * - 장애인 강좌와 일반 강좌를 합쳐 올바른 페이징 데이터를 반환하는지 테스트.
     */
    @Test
    void getAllCourses_ReturnsPaginatedCourses() {
        // Given
        DisabledCourse disabledCourse = DisabledCourse.builder()
                .disabledCourseId(1)
                .busiRegNo("12345678")
                .sportNm("축구")
                .courseNm("장애인 축구 강좌")
                .startTime(LocalTime.of(10, 0))
                .endTime(LocalTime.of(12, 0))
                .weekday("1010100")
                .cityName("서울특별시")
                .districtName("강남구")
                .build();

        GeneralCourse generalCourse = GeneralCourse.builder()
                .generalCourseId(2)
                .busiRegNo("87654321")
                .sportNm("농구")
                .courseNm("일반 농구 강좌")
                .startTime(LocalTime.of(15, 0))
                .endTime(LocalTime.of(17, 0))
                .weekday("0101010")
                .cityName("부산광역시")
                .districtName("해운대구")
                .build();

        when(disabledCourseRepository.findAll()).thenReturn(List.of(disabledCourse));
        when(generalCourseRepository.findAll()).thenReturn(List.of(generalCourse));

        // When
        PaginatedCourseResponseDto result = courseService.getAllCourses(0, 10, null, null, null, null, null);

        // Then
        assertNotNull(result);
        assertEquals(2, result.getTotalCount());
        assertEquals(1, result.getTotalPages());
        assertEquals(2, result.getCourses().size());
    }

}
