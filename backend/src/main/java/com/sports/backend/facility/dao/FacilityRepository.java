package com.sports.backend.facility.dao;

import com.sports.backend.facility.domain.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacilityRepository extends JpaRepository<Facility, Long> {
}