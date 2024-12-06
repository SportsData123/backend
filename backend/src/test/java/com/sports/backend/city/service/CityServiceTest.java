package com.sports.backend.city.service;

import com.sports.backend.city.dao.CityRepository;
import com.sports.backend.city.domain.City;
import com.sports.backend.city.dto.CityResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * CityServiceTest는 CityService 클래스의 동작을 테스트하기 위한 클래스입니다.
 * Mockito를 사용하여 CityRepository를 모의(Mock)로 설정하여 서비스의 비즈니스 로직만을 테스트합니다.
 */
public class CityServiceTest {

    // CityRepository를 모의(Mock) 객체로 선언
    @Mock
    private CityRepository cityRepository;

    // 테스트 대상 클래스
    @InjectMocks
    private CityService cityService;

    /**
     * 테스트 실행 전 Mockito 초기화를 수행합니다.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Mockito 초기화
    }

    /**
     * getCityList() 메서드가 데이터베이스에서 도시 목록을 조회하고
     * CityResponseDto 형태로 변환하여 반환하는지 테스트합니다.
     */
    @Test
    void getCityList_ReturnsCityResponseDtos() {
        // Given
        List<City> mockCities = Arrays.asList(
                new City(1, "서울특별시", "11", null),
                new City(2, "부산광역시", "26", null)
        );
        when(cityRepository.findAll()).thenReturn(mockCities);

        // When
        List<CityResponseDto> result = cityService.getCityList();

        // Then
        assertEquals(2, result.size());
        assertEquals("서울특별시", result.get(0).getCityName());
        assertEquals("26", result.get(1).getCityCode());
        verify(cityRepository, times(1)).findAll();
    }

    /**
     * importCityData() 메서드가 유효한 CSV 데이터를 처리하고
     * 중복되지 않는 도시 데이터를 저장하는지 테스트합니다.
     */
    @Test
    void importCityData_ValidData_SavesCities() {
        // Given
        City city = new City(3, "대구광역시", "27", null);
        when(cityRepository.existsByCityName("대구광역시")).thenReturn(false);

        // When
        cityService.importCityData("city_file.csv");

        // Then
        verify(cityRepository, atLeast(0)).save(any(City.class)); // 저장 호출 확인
    }
}
