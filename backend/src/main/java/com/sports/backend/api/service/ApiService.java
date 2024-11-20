package com.sports.backend.api.service;

import com.sports.backend.api.dto.FacilityApiResponse;
import com.sports.backend.facility.dao.FacilityRepository;
import com.sports.backend.facility.domain.Facility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class ApiService {
    private final RestTemplate restTemplate;
    private final FacilityRepository facilityRepository;

    @Value("${api.base-url}")
    private String apiBaseUrl;

    @Value("${api.service-key}")
    private String serviceKey;

    private static final Logger logger = LoggerFactory.getLogger(ApiService.class);

    public ApiService(RestTemplate restTemplate, FacilityRepository facilityRepository) {
        this.restTemplate = restTemplate;
        this.facilityRepository = facilityRepository;
    }

    public List<Facility> fetchAndSaveFacilityData(int pageNo, int numOfRows) {
        try {
            // URL 구성
            String encodedServiceKey = URLEncoder.encode(serviceKey, StandardCharsets.UTF_8);
            String url = String.format("%s?serviceKey=%s&pageNo=%d&numOfRows=%d&resultType=json",
                    apiBaseUrl, encodedServiceKey, pageNo, numOfRows);

            logger.info("API 호출 URL: {}", url);

            // API 호출
            FacilityApiResponse response = restTemplate.getForObject(url, FacilityApiResponse.class);
            logger.info("API Response: {}", response);

            // 응답 검증
            if (response == null || response.getResponse() == null) {
                logger.warn("API 응답이 비어 있습니다.");
                return List.of();
            }

            // 데이터 변환 및 저장
            List<Facility> facilities = response.toFacilityEntities();
            if (facilities.isEmpty()) {
                logger.warn("변환된 데이터가 없습니다.");
                return List.of();
            }

            facilityRepository.saveAll(facilities);
            logger.info("총 {}개의 데이터를 저장했습니다.", facilities.size());
            return facilities;
        } catch (Exception e) {
            logger.error("API 호출 또는 데이터 저장 중 오류 발생", e);
            return List.of();
        }
    }
}
