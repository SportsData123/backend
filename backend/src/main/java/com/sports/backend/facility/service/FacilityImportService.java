package com.sports.backend.facility.service;

import com.sports.backend.api.service.ApiService;
import com.sports.backend.facility.domain.Facility;
import com.sports.backend.facility.dao.FacilityRepository;
import com.sports.backend.facilityBusiness.domain.FacilityBusiness;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacilityImportService {
    private static final Logger logger = LoggerFactory.getLogger(FacilityImportService.class);

    private final ApiService apiService;
    private final FacilityRepository facilityRepository;

    public FacilityImportService(ApiService apiService, FacilityRepository facilityRepository) {
        this.apiService = apiService;
        this.facilityRepository = facilityRepository;
    }

    public void importFacilities(int pageNo, int numOfRows) {
        boolean hasMoreData;

        do {
            logger.info("Fetching facilities from API: pageNo={}, numOfRows={}", pageNo, numOfRows);

            List<Facility> facilities = apiService.fetchAndSaveFacilityData(pageNo, numOfRows);

            if (facilities.isEmpty()) {
                logger.warn("No data fetched from API for pageNo={}, numOfRows={}", pageNo, numOfRows);
                hasMoreData = false;
            } else {
                facilityRepository.saveAll(facilities); // DB에 저장
                logger.info("Saved {} facilities to the database.", facilities.size());
                hasMoreData = facilities.size() == numOfRows; // 페이지에 데이터가 가득 찬 경우에만 다음 페이지로 이동
                pageNo++;
            }
        } while (hasMoreData);

        logger.info("Facility data import completed.");
    }
}
