package com.sports.backend.sport.dao;

import com.sports.backend.sport.domain.Sport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportRepository extends JpaRepository<Sport, Integer> {
}