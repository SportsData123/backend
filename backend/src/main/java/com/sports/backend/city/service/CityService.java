package com.sports.backend.city.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.sports.backend.city.dao.CityRepository;
import com.sports.backend.city.domain.City;
import com.sports.backend.city.dto.CityResponseDto;
import com.sports.backend.city.mapper.CityMapper;
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

/**
 * CityService는 도시 데이터에 대한 비즈니스 로직을 처리하는 서비스 클래스입니다.
 * - CSV 파일에서 도시 데이터를 가져와 저장
 * - 데이터베이스에서 도시 데이터를 조회
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CityService {
    private final CityRepository cityRepository;

    /**
     * 어플리케이션 초기화 시 데이터를 로드합니다.
     * - 데이터가 없는 경우 CSV 파일을 가져와 로드합니다.
     */
    @PostConstruct
    public void addressInit() {
        if (!dataAlreadyLoaded(cityRepository)) {
            log.info("City 데이터가 비어 있습니다. CSV 파일을 로드합니다.");
            importCityData("city_file.csv");
        } else {
            log.info("City 데이터가 이미 로드되어 있습니다.");
        }
    }

    /**
     * 데이터가 이미 로드되었는지 확인합니다.
     *
     * @param repository CityRepository 객체
     * @return boolean 데이터가 존재하면 true, 없으면 false
     */
    private boolean dataAlreadyLoaded(CityRepository repository) {
        return repository.existsById(1); // 특정 ID가 존재하는지만 확인
    }

    /**
     * CSV 파일에서 데이터를 가져와 데이터베이스에 저장합니다.
     *
     * @param fileName CSV 파일 이름
     */
    @Transactional
    public void importCityData(String fileName) {
        try {
            log.info("CSV 파일 {} 로드 시작", fileName);

            File file = loadCsvFile(fileName);
            if (!file.exists()) {
                log.error("파일이 존재하지 않습니다: {}", file.getAbsolutePath());
                return;
            }

            try (FileReader reader = new FileReader(file);
                 CSVReader csvReader = new CSVReader(reader)) {

                csvReader.readNext(); // 헤더 스킵
                String[] nextRecord;

                while ((nextRecord = csvReader.readNext()) != null) {
                    try {
                        City city = CityMapper.fromCsvRecord(nextRecord);
                        if (city != null && !cityRepository.existsByCityName(city.getCityName())) {
                            cityRepository.save(city);
                        }
                    } catch (Exception e) {
                        log.warn("데이터 처리 중 오류 발생. 레코드: {}, 이유: {}", nextRecord, e.getMessage());
                    }
                }
            }

            log.info("CSV 데이터 저장 완료.");
        } catch (IOException | CsvValidationException e) {
            log.error("CSV 파일 처리 중 오류 발생: {}", e.getMessage(), e);
        }
    }

    /**
     * 주어진 파일 이름으로 CSV 파일을 로드합니다.
     *
     * @param fileName CSV 파일 이름
     * @return File 로드된 파일 객체
     * @throws IOException 파일 읽기 중 오류가 발생할 경우
     */
    private File loadCsvFile(String fileName) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(
                URLDecoder.decode(
                        Objects.requireNonNull(classLoader.getResource(fileName)).getFile(),
                        StandardCharsets.UTF_8
                )
        );
    }

    /**
     * 데이터베이스에서 모든 도시 데이터를 조회합니다.
     *
     * @return List<CityResponseDto> 도시 데이터를 반환
     */
    public List<CityResponseDto> getCityList() {
        return cityRepository.findAll().stream()
                .map(CityMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
