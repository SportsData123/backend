package com.sports.backend.city.service;

import com.sports.backend.city.dao.CityRepository;
import com.sports.backend.city.domain.City;
import com.sports.backend.city.dto.CityDto;
import com.sports.backend.city.dto.CityResponseDto;
import com.sports.backend.config.CsvConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.io.File;

@Service
public class CityService {
    private static final Logger logger = LoggerFactory.getLogger(CityService.class);

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Transactional
    public void importCityData() {
        try {
            // 상대 경로에 위치한 파일 읽기
            File csvFile = new ClassPathResource("city_file.csv").getFile();

            // 기존의 CsvConfig.parseCsv 메서드 호출
            List<CityDto> cityCsvDtos = CsvConfig.parseCsv(csvFile.getAbsolutePath(), CityDto.class);
            cityCsvDtos.forEach(dto -> System.out.println("로드된 데이터: " + dto));

            if (cityCsvDtos.isEmpty()) {
                logger.warn("CSV 파일이 비어 있습니다. 파일 경로: {}", csvFile.getAbsolutePath());
                return;
            }

            for (CityDto dto : cityCsvDtos) {
                logger.debug("CSV 데이터 처리 중: {}", dto);

                try {
                    String cityCode = dto.getCityCode();
                    String cityName = dto.getCityName();
                    String exist = dto.getExist();

                    if (cityCode != null && "존재".equals(exist)) {
                        String twoDigitCityCode = cityCode.substring(0, 2);
                        String trimmedCityName = cityName.split(" ")[0];

                        logger.debug("현재 처리 중인 데이터: {}", dto);

                        if (!cityRepository.existsByCityCode(twoDigitCityCode)) {
                            City city = City.builder()
                                    .cityName(trimmedCityName)
                                    .cityCode(twoDigitCityCode)
                                    .build();
                            cityRepository.save(city);
                            logger.info("도시 저장 성공: {}, {}", trimmedCityName, twoDigitCityCode);
                        }
                    } else {
                        logger.warn("유효하지 않은 데이터: {}", dto);
                    }
                } catch (Exception e) {
                    logger.error("도시 데이터를 처리하는 중 오류 발생. 데이터: {}, 이유: {}", dto, e.getMessage());
                }
            }

            logger.info("도시 데이터 저장 완료.");
        } catch (Exception e) {
            throw new RuntimeException("CSV 파일 처리 중 오류 발생: " + e.getMessage(), e);
        }
    }

    public List<CityResponseDto> getCityList() {
        return cityRepository.findAll().stream()
                .map(city -> new CityResponseDto(city.getCityId(), city.getCityName(), city.getCityCode()))
                .collect(Collectors.toList());
    }
}
