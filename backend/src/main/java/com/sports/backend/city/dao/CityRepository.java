package com.sports.backend.city.dao;

import com.sports.backend.city.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
    boolean existsByCityName(String cityName);
}