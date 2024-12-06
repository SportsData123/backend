package com.sports.backend.city.dao;

import com.sports.backend.city.domain.City;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // JPA 관련 테스트에 최적화된 어노테이션
public class CityRepositoryTest {
    @Autowired
    private CityRepository cityRepository;

    /**
     * existsByCityName 테스트: 특정 도시 이름이 존재하는지 확인합니다.
     */
    @Test
    void existsByCityName_ReturnsTrueIfExists() {
        // Given: 테스트용 City 객체 저장
        City city = new City(1, "서울특별시", "11", null);
        cityRepository.save(city);

        // When: existsByCityName 호출
        boolean exists = cityRepository.existsByCityName("서울특별시");

        // Then: 결과 검증
        assertTrue(exists);
    }

    /**
     * findByCityName 테스트: 도시 이름으로 City를 조회합니다.
     */
    @Test
    void findByCityName_ReturnsCityIfExists() {
        // Given: 테스트용 City 객체 저장
        City city = new City(2, "부산광역시", "26", null);
        cityRepository.save(city);

        // When: findByCityName 호출
        City foundCity = cityRepository.findByCityName("부산광역시");

        // Then: 결과 검증
        assertNotNull(foundCity);
        assertEquals("26", foundCity.getCityCode());
    }
}
