package com.sports.backend.city.dao;

import com.sports.backend.city.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * CityRepository는 JPA를 활용하여 City 엔티티와 데이터베이스 간의 상호작용을 담당합니다.
 * JpaRepository를 상속받아 기본적인 CRUD 기능과 더불어 추가적인 메서드를 제공합니다.
 */

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

    /**
     * 특정 도시 이름이 데이터베이스에 존재하는지 확인합니다.
     *
     * @param cityName 확인하려는 도시 이름
     * @return boolean 도시 이름이 존재하면 true, 그렇지 않으면 false
     */
    boolean existsByCityName(String cityName);

    /**
     * 도시 이름을 통해 City 엔티티를 조회합니다.
     *
     * @param cityName 조회하려는 도시 이름
     * @return City 해당 도시 이름에 해당하는 City 객체를 반환
     */
    City findByCityName(String cityName);
}