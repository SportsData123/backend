package com.sports.backend.generalfacility.service;

import com.sports.backend.generalfacility.dao.GeneralFacilityRepository;
import com.sports.backend.generalfacility.domain.GeneralFacility;
import com.sports.backend.generalfacility.dto.GeneralFacilityDto;
import com.sports.backend.generalfacility.mapper.GeneralFacilityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * `GeneralFacilityService`는 일반 시설 데이터를 관리하는 서비스 클래스입니다.
 * 외부 API 호출을 통해 데이터를 가져오고, 데이터베이스에 저장하거나 조회하는 기능을 제공합니다.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class GeneralFacilityService {

    private final GeneralFacilityRepository generalFacilityRepository;
    private final GeneralFacilityApiClient generalFacilityApiClient;
    private final GeneralFacilityMapper generalFacilityMapper;

    /**
     * 일반 시설 데이터가 이미 로드되었는지 확인합니다.
     *
     * @return 데이터베이스에 데이터가 존재하면 `true`, 아니면 `false`
     */
    private boolean isGeneralFacilityDataLoaded() {
        long count = generalFacilityRepository.count();
        log.info("현재 Facility 테이블에 저장된 데이터 수: {}", count);
        return count > 0;
    }

    /**
     * 외부 API를 호출하여 일반 시설 데이터를 가져오고, 데이터베이스에 저장합니다.
     *
     * @param serviceKey API 호출에 필요한 인증키
     * @param numOfRows  한 페이지에 가져올 데이터 개수
     */
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
            List<GeneralFacilityDto> generalFacilities = generalFacilityApiClient.fetchData(serviceKey, pageNo, numOfRows);

            if (generalFacilities.isEmpty()) {
                log.warn("API에서 더 이상 가져올 데이터가 없습니다.");
                break;
            }

            // Facility 엔티티로 변환 후 저장
            List<GeneralFacility> entities = generalFacilities.stream()
                    .map(generalFacilityMapper::toEntity)
                    .toList();

            generalFacilityRepository.saveAll(entities);

            log.info("페이지 {}: {}개의 데이터가 저장되었습니다.", pageNo, entities.size());

            // 다음 페이지로 이동
            pageNo++;
            hasMoreData = generalFacilities.size() == numOfRows; // 현재 페이지에 데이터가 가득 차 있으면 더 가져옴
        } while (hasMoreData);
    }
}
