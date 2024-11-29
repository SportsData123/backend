package com.sports.backend.facility.service;

import com.sports.backend.city.dao.CityRepository;
import com.sports.backend.city.domain.City;
import com.sports.backend.disabledfacility.dao.DisabledFacilityRepository;
import com.sports.backend.disabledfacility.domain.DisabledFacility;
import com.sports.backend.district.dao.DistrictRepository;
import com.sports.backend.district.domain.District;
import com.sports.backend.facility.dao.FacilityRepository;
import com.sports.backend.facility.domain.Facility;
import com.sports.backend.facility.dto.FacilityDto;
import com.sports.backend.facility.dto.FacilityResponseDto;
import com.sports.backend.generalfacility.dao.GeneralFacilityRepository;
import com.sports.backend.generalfacility.domain.GeneralFacility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class FacilityService {
    private final FacilityRepository facilityRepository;
    private final GeneralFacilityRepository generalFacilityRepository;
    private final DisabledFacilityRepository disabledFacilityRepository;
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
    public List<FacilityResponseDto> getFacilities(String cityId, String districtId, String isAccessibleForDisabled, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);

        // Facility 데이터 조회
        List<Facility> facilities = facilityRepository.findAllWithFilters(
                cityId,
                districtId,
                isAccessibleForDisabled,
                pageable
        );

        // GeneralFacility 데이터 조회
        List<GeneralFacility> generalFacilities = generalFacilityRepository.findWithFilters(
                cityId,
                districtId,
                isAccessibleForDisabled,
                pageable
        );

        // DisabledFacility 데이터 조회
        List<DisabledFacility> disabledFacilities = disabledFacilityRepository.findWithFilters(
                cityId,
                districtId,
                isAccessibleForDisabled,
                pageable
        );

        // 통합 결과 반환
        return combineResults(facilities, generalFacilities, disabledFacilities);
    }

    private List<FacilityResponseDto> combineResults(
            List<Facility> facilities,
            List<GeneralFacility> generalFacilities,
            List<DisabledFacility> disabledFacilities) {

        Set<String> uniqueKeys = new HashSet<>(); // 중복 제거를 위한 Set
        return Stream.of(
                        facilities.stream()
                                .flatMap(facility -> facility.getDisabledFacility().stream()
                                        .map(disabledFacility -> mapToResponseDto(facility, facility.getGeneralFacility(), disabledFacility))), // Facility와 DisabledFacility 연결된 경우
                        generalFacilities.stream()
                                .map(gf -> mapToResponseDto(gf.getFacility(), gf, null)), // GeneralFacility 데이터
                        disabledFacilities.stream()
                                .map(df -> mapToResponseDto(
                                        df.getFacility(),
                                        Optional.ofNullable(df.getFacility())
                                                .map(Facility::getGeneralFacility)
                                                .orElse(null),
                                        df))
                ).flatMap(stream -> stream)
                .filter(dto -> uniqueKeys.add(generateUniqueKey(dto)))
                .toList();
    }

    private String generateUniqueKey(FacilityResponseDto dto) {
        return String.join("_",
                Optional.ofNullable(dto.getFacilityName()).orElse(""),
                Optional.ofNullable(dto.getCityName()).orElse(""),
                Optional.ofNullable(dto.getDistrictName()).orElse(""));
    }



    private FacilityResponseDto mapToResponseDto(Facility facility, GeneralFacility generalFacility, DisabledFacility disabledFacility) {
        return FacilityResponseDto.builder()
                // Facility 데이터
                .facilityId(facility != null ? facility.getFacilityId() : null)
                .facilityName(facility != null ? facility.getFacilityName() :
                        (generalFacility != null ? generalFacility.getFacilName() :
                                (disabledFacility != null ? disabledFacility.getFacilName() : null)))
                .facilityType(facility != null ? facility.getFacilityType() : null)
                .facilityStatus(facility != null ? facility.getFacilityStatus() : null)
                .roadAddress(facility != null ? facility.getRoadAddress() : null)
                .detailAddress(facility != null ? facility.getDetailAddress() : null)
                .zipCode(facility != null ? facility.getZipCode() : null)
                .longitude(facility != null ? facility.getLongitude() : null)
                .latitude(facility != null ? facility.getLatitude() : null)
                .inOutType(facility != null ? facility.getInOutType() : null)
                .nationFlag(facility != null ? facility.getNationFlag() : null)
                .cityName(facility != null ? facility.getCityName() :
                        (generalFacility != null ? generalFacility.getCityName() :
                                (disabledFacility != null ? disabledFacility.getCityName() : null)))
                .districtName(facility != null ? facility.getDistrictName() :
                        (generalFacility != null ? generalFacility.getDistrictName() :
                                (disabledFacility != null ? disabledFacility.getDistrictName() : null)))

                // GeneralFacility 데이터
                .generalFacilityId(generalFacility != null ? generalFacility.getGeneralFacilityId() : null)
                .generalBrno(generalFacility != null ? generalFacility.getBrno() : null)
                .generalFacilSn(generalFacility != null ? generalFacility.getFacilSn() : null)
                .generalMainEventName(generalFacility != null ? generalFacility.getMainEventName() : null)
                .generalResTelno(generalFacility != null ? generalFacility.getResTelno() : null)

                // DisabledFacility 데이터
                .disabledFacilityId(disabledFacility != null ? disabledFacility.getDisabledFacilityId() : null)
                .disabledResTelno(disabledFacility != null ? disabledFacility.getResTelno() : null)
                .disabledMainEventName(disabledFacility != null ? disabledFacility.getMainEventName() : null)
                .isAccessibleForDisabled(facility != null ? facility.getIsAccessibleForDisabled() :
                        (disabledFacility != null ? "Y" : null))
                .build();
    }

    @Transactional(readOnly = true)
    public Map<String, Object> getPaginationInfo(String cityId, String districtId, String isAccessibleForDisabled, int size) {
        long totalCount = facilityRepository.countUnifiedByFilters(cityId, districtId, isAccessibleForDisabled);
        int totalPages = (int) Math.ceil((double) totalCount / size);

        Map<String, Object> paginationInfo = new HashMap<>();
        paginationInfo.put("totalCount", totalCount);
        paginationInfo.put("totalPages", totalPages);
        paginationInfo.put("pageSize", size);

        return paginationInfo;
    }


}