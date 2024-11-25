package com.sports.backend.generalfacility.dao;

import com.sports.backend.disabledfacility.domain.DisabledFacility;
import com.sports.backend.generalfacility.domain.GeneralFacility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneralFacilityRepository extends JpaRepository<GeneralFacility, Integer>{
}
