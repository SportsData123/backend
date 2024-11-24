package com.sports.backend.district.dao;

import com.sports.backend.city.domain.City;
import com.sports.backend.district.domain.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {
    List<District> findByCity_CityId(int cityId);

    District findByDistrictNameAndCityId(String districtName, int cityId);
}
