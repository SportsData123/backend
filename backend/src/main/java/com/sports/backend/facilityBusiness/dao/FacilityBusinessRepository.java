package com.sports.backend.facilityBusiness.dao;

import com.sports.backend.facilityBusiness.domain.FacilityBusiness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilityBusinessRepository extends JpaRepository<FacilityBusiness, Long> {
    // FacilityBusiness 엔터티의 facilityName으로 엔터티 검색
    FacilityBusiness findByFacilityName(String facilityName);
}