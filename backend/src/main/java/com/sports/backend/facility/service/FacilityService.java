package com.sports.backend.facility.service;

import com.sports.backend.facility.dao.FacilityRepository;
import com.sports.backend.facility.domain.Facility;
import com.sports.backend.facility.dto.FacilityRequestDto;
import com.sports.backend.facility.dto.FacilityResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacilityService {
    private final FacilityRepository facilityRepository;

    public FacilityService(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    public List<FacilityResponseDto> getFacilities(FacilityRequestDto requestDto) {
        Pageable pageable = PageRequest.of(requestDto.getPage() - 1, requestDto.getSize());
        Page<Facility> facilities = facilityRepository.findFacilities(
                requestDto.getCityCode(),
                requestDto.getDistrictCode(),
                requestDto.getIsAccessibleForDisabled(),
                pageable
        );

        return facilities.getContent().stream()
                .map(facility -> new FacilityResponseDto(
                        facility.getFacilityId(),
                        facility.getFacilityName().getFacilityName(),
                        facility.getFacilityType(),
                        facility.getFacilityStatus(),
                        facility.getRoadAddress(),
                        facility.getLatitude(),
                        facility.getLongitude(),
                        facility.getFacilityName().getIsAccessibleForDisabled() // 1:1 관계 활용
                ))
                .collect(Collectors.toList());
    }
}
