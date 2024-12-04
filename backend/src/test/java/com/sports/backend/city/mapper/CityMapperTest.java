package com.sports.backend.city.mapper;

import com.sports.backend.city.domain.City;
import com.sports.backend.city.dto.CityResponseDto;
import com.sports.backend.city.mapper.CityMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * CityMapperTest는 CityMapper 클래스의 데이터 변환 기능을 테스트합니다.
 * - CSV 레코드 배열을 City 객체로 변환
 * - City 객체를 CityResponseDto로 변환
 */

public class CityMapperTest {
    /**
     * fromCsvRecord 메서드 테스트: 유효한 CSV 레코드 배열을 입력했을 때
     * - City 객체가 정상적으로 반환되는지 확인합니다.
     */
    @Test
    void fromCsvRecord_ValidRecord_ReturnsCity() {
        // Given: 유효한 CSV 레코드
        String[] record = {"1168000000", "서울특별시 강남구", "존재"};

        // When: fromCsvRecord 호출
        City city = CityMapper.fromCsvRecord(record);

        // Then: City 객체가 반환되고 필드 값이 올바른지 확인
        assertNotNull(city);
        assertEquals("11", city.getCityCode());
        assertEquals("서울특별시", city.getCityName());
    }

    /**
     * fromCsvRecord 메서드 테스트: "폐지" 상태의 CSV 레코드 배열을 입력했을 때
     * - null이 반환되는지 확인합니다.
     */
    @Test
    void fromCsvRecord_InvalidRecord_ReturnsNull() {
        // Given: 폐지된 상태의 CSV 레코드
        String[] record = {"1168000000", "서울특별시 강남구", "폐지"};

        // When: fromCsvRecord 호출
        City city = CityMapper.fromCsvRecord(record);

        // Then: null이 반환되는지 확인
        assertNull(city);
    }

    /**
     * toResponseDto 메서드 테스트: 유효한 City 객체를 입력했을 때
     * - CityResponseDto 객체가 정상적으로 반환되는지 확인합니다.
     */
    @Test
    void toResponseDto_ValidCity_ReturnsCityResponseDto() {
        // Given: 유효한 City 객체
        City city = City.builder()
                .cityId(1)
                .cityCode("11")
                .cityName("서울특별시")
                .build();

        // When: toResponseDto 호출
        CityResponseDto dto = CityMapper.toResponseDto(city);

        // Then: CityResponseDto 객체가 반환되고 필드 값이 올바른지 확인
        assertNotNull(dto);
        assertEquals(1, dto.getCityId());
        assertEquals("11", dto.getCityCode());
        assertEquals("서울특별시", dto.getCityName());
    }
}
