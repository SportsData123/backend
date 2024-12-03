package com.sports.backend.generalfacility.service;

import com.sports.backend.generalfacility.dao.GeneralFacilityRepository;
import com.sports.backend.generalfacility.domain.GeneralFacility;
import com.sports.backend.generalfacility.dto.GeneralFacilityDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GeneralFacilityService {
    private final GeneralFacilityRepository generalFacilityRepository;
    private final GeneralFacilityApiClient generalFacilityApiClient;

    private boolean isGeneralFacilityDataLoaded() {
        long count = generalFacilityRepository.count();
        log.info("현재 Facility 테이블에 저장된 데이터 수: {}", count);
        return count > 0;
    }

    @Transactional
    public void fetchAndSaveGeneralFacilities(String serviceKey, int numOfRows) {
        if(isGeneralFacilityDataLoaded()){
            log.info("GeneralFacility 데이터가 이미 로드되어 있습니다. 저장 과정을 생략합니다.");
            return;
        }

        int pageNo = 1;
        boolean hasMoreData;

        do {
            // 한 페이지의 데이터를 가져옴
            List<GeneralFacilityDto> generalFacilities = generalFacilityApiClient.fetchGeneralFacilities(serviceKey, pageNo, numOfRows);

            if (generalFacilities.isEmpty()) {
                log.warn("API에서 더 이상 가져올 데이터가 없습니다.");
                break;
            }

            // Facility 엔티티로 변환 후 저장
            List<GeneralFacility> entities = generalFacilities.stream()
                    .map(this::mapToEntity)
                    .toList();

            generalFacilityRepository.saveAll(entities);

            log.info("페이지 {}: {}개의 데이터가 저장되었습니다.", pageNo, entities.size());

            // 다음 페이지로 이동
            pageNo++;
            hasMoreData = generalFacilities.size() == numOfRows; // 현재 페이지에 데이터가 가득 차 있으면 더 가져옴
        } while (hasMoreData);
    }

    private GeneralFacility mapToEntity(GeneralFacilityDto dto) {
        return GeneralFacility.builder()
                .facilName(dto.getFacilName())
                .resTelno(dto.getResTelno())
                .mainEventName(dto.getMainEventName())
                .cityCode(dto.getCityCode())
                .cityName(dto.getCityName())
                .districtCode(dto.getDistrictCode())
                .districtName(dto.getDistrictName())
                .brno(dto.getBrno())
                .facilSn(dto.getFacilSn())
                .roadAddr(dto.getRoadAddr())
                .faciDaddr(dto.getFaciDaddr())
                .build();
    }
}
