package com.sports.backend.city.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.sports.backend.city.dao.CityRepository;
import com.sports.backend.city.domain.City;
import com.sports.backend.city.dto.CityResponseDto;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.io.File;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CityService {
    private final CityRepository cityRepository;

    @PostConstruct
    public void addressInit() {
        if (!dataAlreadyLoaded(cityRepository)) {
            log.info("City 데이터가 비어 있습니다. CSV 파일을 로드합니다.");
            importCityData("city_file.csv");
        } else {
            log.info("City 데이터가 이미 로드되어 있습니다.");
        }
    }

    private boolean dataAlreadyLoaded(CityRepository repository) {
        return repository.count() > 0;
    }

    @Transactional
    public void importCityData(String fileName) {
        try {
            log.info("CSV 파일 {} 로드 시작", fileName);

            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(
                    URLDecoder.decode(
                            Objects.requireNonNull(classLoader.getResource(fileName)).getFile(),
                            StandardCharsets.UTF_8
                    )
            );

            if (!file.exists()) {
                log.error("파일이 존재하지 않습니다: {}", file.getAbsolutePath());
                return;
            }
            FileReader reader = new FileReader(file);
            CSVReader csvReader = new CSVReader(reader);

            csvReader.readNext(); // 헤더 스킵
            String[] nextRecord;
            List<City> cities = new ArrayList<>();

            while ((nextRecord = csvReader.readNext()) != null) {
                try {
                    String cityCode = nextRecord[0].substring(0, 2);  // 법정동코드 (왼쪽 2자리)
                    String cityName = nextRecord[1].split(" ")[0];    // 법정동명 (첫 단어)
                    String exist = nextRecord[2];                     // 폐지여부

                    log.debug("처리 중 데이터: 법정동코드={}, 법정동명={}, 폐지여부={}", cityCode, cityName, exist);

                    if ("존재".equals(exist) && !cityRepository.existsByCityName(cityName)) {
                        City city = City.builder()
                                .cityCode(cityCode)
                                .cityName(cityName)
                                .build();
                        cityRepository.save(city);  // 중복 확인 후 바로 저장
                    }
                } catch (Exception e) {
                    log.warn("데이터 처리 중 오류 발생. 레코드: {}, 이유: {}", nextRecord, e.getMessage());
                }
            }

            log.info("CSV 데이터 저장 완료.");
            csvReader.close();
        } catch (IOException | CsvValidationException e) {
            log.error("CSV 파일 처리 중 오류 발생: {}", e.getMessage(), e);
        }
    }

    public List<CityResponseDto> getCityList() {
        return cityRepository.findAll().stream()
                .map(city -> new CityResponseDto(city.getCityId(), city.getCityName(), city.getCityCode()))
                .collect(Collectors.toList());
    }
}
