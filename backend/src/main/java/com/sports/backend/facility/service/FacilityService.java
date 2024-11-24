package com.sports.backend.facility.service;

import com.sports.backend.city.dao.CityRepository;
import com.sports.backend.city.domain.City;
import com.sports.backend.district.dao.DistrictRepository;
import com.sports.backend.district.domain.District;
import com.sports.backend.facility.dao.FacilityRepository;
import com.sports.backend.facility.domain.Facility;
import com.sports.backend.facility.dto.FacilityDto;
import com.sports.backend.facility.dto.FacilityResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class FacilityService {
    private final FacilityRepository facilityRepository;
    private final CityRepository cityRepository;
    private final DistrictRepository districtRepository;
    private final FacilityApiClient facilityApiClient;

    private boolean isFacilityDataLoaded() {
        long count = facilityRepository.count();
        log.info("현재 Facility 테이블에 저장된 데이터 수: {}", count);
        return count > 0;
    }

    @Transactional
    public void fetchAndSaveAllFacilities(String serviceKey, int numOfRows) {
        if (isFacilityDataLoaded()) {
            log.info("Facility 데이터가 이미 로드되어 있습니다. 저장 과정을 생략합니다.");
            return;
        }

        int pageNo = 1;
        boolean hasMoreData;

        do {
            // 한 페이지의 데이터를 가져옴
            List<FacilityDto> facilities = facilityApiClient.fetchFacilities(serviceKey, pageNo, numOfRows);

            if (facilities.isEmpty()) {
                log.warn("API에서 더 이상 가져올 데이터가 없습니다.");
                break;
            }

            // Facility 엔티티로 변환 후 저장
            List<Facility> entities = facilities.stream()
                    .map(this::mapToEntity)
                    .toList();

            facilityRepository.saveAll(entities);

            log.info("페이지 {}: {}개의 데이터가 저장되었습니다.", pageNo, entities.size());

            // 다음 페이지로 이동
            pageNo++;
            hasMoreData = facilities.size() == numOfRows; // 현재 페이지에 데이터가 가득 차 있으면 더 가져옴
        } while (hasMoreData);
    }


    private Facility mapToEntity(FacilityDto dto) {
        City city = cityRepository.findByCityName(dto.getCityName());
        District district = null;

        if (city != null) {
            district = districtRepository.findByDistrictNameAndCity_CityId(dto.getDistrictName(), city.getCityId());
        }

        return Facility.builder()
                .facilityName(dto.getFacilityName())
                .facilityType(dto.getFacilityType())
                .facilityStatus(dto.getFacilityStatus())
                .roadAddress(dto.getRoadAddress())
                .detailAddress(dto.getDetailAddress())
                .zipCode(dto.getZipCode())
                .longitude(dto.getLongitude())
                .latitude(dto.getLatitude())
                .inOutType(dto.getInOutType())
                .nationFlag(dto.getNationFlag())
                .city(city)
                .district(district)
                .build();
    }

    @Transactional(readOnly = true)
    public List<FacilityResponseDto> getFacilities(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size); // Spring Data JPA 페이징 사용
        Page<Facility> facilityPage = facilityRepository.findAll(pageable);

        return facilityPage.getContent().stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    private FacilityResponseDto mapToResponseDto(Facility entity) {
        return new FacilityResponseDto(
                entity.getFacilityId(),
                entity.getFacilityName(),
                entity.getFacilityType(),
                entity.getFacilityStatus(),
                entity.getRoadAddress(),
                entity.getDetailAddress(),
                entity.getZipCode(),
                entity.getLongitude(),
                entity.getLatitude(),
                entity.getInOutType(),
                entity.getNationFlag(),
                entity.getCityName(),
                entity.getDistrictName()
        );
    }

}