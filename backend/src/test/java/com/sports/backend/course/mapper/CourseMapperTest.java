package com.sports.backend.course.mapper;

import com.sports.backend.course.dto.CourseResponseDto;
import com.sports.backend.disabledcourse.domain.DisabledCourse;
import com.sports.backend.generalcourse.domain.GeneralCourse;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * CourseMapperTest는 CourseMapper 클래스의 메서드를 테스트하여
 * 도메인 객체(DisabledCourse, GeneralCourse)를 DTO 객체로 올바르게 변환하는지 검증합니다.
 */
public class CourseMapperTest {

    /**
     * toDto 메서드가 DisabledCourse 객체를 올바르게 변환하는지 테스트합니다.
     */
    @Test
    void toDto_DisabledCourse_ReturnsDto() {
        // Given
        DisabledCourse disabledCourse = DisabledCourse.builder()
                .disabledCourseId(1)
                .busiRegNo("12345678")
                .sportNm("축구")
                .courseNm("장애인 축구 강좌")
                .startTime(LocalTime.of(10, 0))
                .endTime(LocalTime.of(12, 0))
                .weekday("1010100")
                .courseSetaDesc("장애인 전용 축구 강좌")
                .settlAmt("10000")
                .cityName("서울특별시")
                .districtName("강남구")
                .roadAddr("강남대로 123")
                .faciDaddr("건물 2층")
                .latitude(37.1234)
                .longitude(127.5678)
                .build();

        // When
        CourseResponseDto dto = CourseMapper.toDto(disabledCourse);

        // Then
        assertNotNull(dto);
        assertEquals(1, dto.getCourseId());
        assertEquals("12345678", dto.getBusiRegNo());
        assertEquals("축구", dto.getSportName());
        assertEquals("Y", dto.getIsAccessibleForDisabled());
    }

    /**
     * toDto 메서드가 GeneralCourse 객체를 올바르게 변환하는지 테스트합니다.
     */
    @Test
    void toDto_GeneralCourse_ReturnsDto() {
        // Given
        GeneralCourse generalCourse = GeneralCourse.builder()
                .generalCourseId(2)
                .busiRegNo("87654321")
                .sportNm("농구")
                .courseNm("일반 농구 강좌")
                .startTime(LocalTime.of(15, 0))
                .endTime(LocalTime.of(17, 0))
                .weekday("0101010")
                .courseSetaDesc("모두를 위한 농구 강좌")
                .cityName("부산광역시")
                .districtName("해운대구")
                .roadAddr("해운대로 456")
                .faciDaddr("건물 3층")
                .latitude(35.1234)
                .longitude(129.5678)
                .build();

        // When
        CourseResponseDto dto = CourseMapper.toDto(generalCourse);

        // Then
        assertNotNull(dto);
        assertEquals(2, dto.getCourseId());
        assertEquals("87654321", dto.getBusiRegNo());
        assertEquals("농구", dto.getSportName());
        assertEquals("N", dto.getIsAccessibleForDisabled());
    }
}
