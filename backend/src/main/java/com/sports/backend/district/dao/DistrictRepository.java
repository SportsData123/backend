package com.sports.backend.district.dao;

import com.sports.backend.district.domain.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {
    boolean existsByDistrictCode(String districtCode);
}
