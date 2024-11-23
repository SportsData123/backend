package com.sports.backend.district.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.sports.backend.city.dao.CityRepository;
import com.sports.backend.district.dao.DistrictRepository;
import com.sports.backend.district.domain.District;
import com.sports.backend.district.dto.DistrictResponseDto;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileReader;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DistrictService {

    private final DistrictRepository districtRepository;
    private final CityRepository cityRepository;

    @PostConstruct
    public void initDistricts() {
        if (!dataAlreadyLoaded(cityRepository)) {
            log.info("District 데이터가 비어 있습니다. CSV 파일을 로드합니다.");
            importDistrictData("city_file.csv");
        } else {
            log.info("District 데이터가 이미 로드되어 있습니다.");
        }
    }

    @Transactional
    public void importDistrictData(String fileName) {
        try {
            log.info("CSV 파일 {} 로드 시작", fileName);

            File file = loadFile(fileName);
            if (file == null) return;

            try (CSVReader csvReader = new CSVReader(new FileReader(file))) {
                csvReader.skip(1); // 헤더 스킵

                String[] record;
                int lineCount = 0;
                while ((record = csvReader.readNext()) != null) {
                    lineCount++;
                    processRecord(record);
                }
                log.info("CSV 파일 처리 완료. 총 {}개 레코드 처리됨.", lineCount);
            }

            log.info("CSV 데이터 저장 완료.");
        } catch (Exception e) {
            log.error("CSV 파일 처리 중 오류 발생: {}", e.getMessage(), e);
        }
    }

    private boolean dataAlreadyLoaded(CityRepository repository) {
        return repository.count() > 0;
    }


    private File loadFile(String fileName) {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            String decodedPath = URLDecoder.decode(
                    Objects.requireNonNull(classLoader.getResource(fileName)).getFile(),
                    StandardCharsets.UTF_8
            );
            File file = new File(decodedPath);

            if (!file.exists()) {
                log.error("파일이 존재하지 않습니다: {}", file.getAbsolutePath());
                return null;
            }

            return file;
        } catch (Exception e) {
            log.error("파일 로드 중 오류 발생: {}", e.getMessage());
            return null;
        }
    }

    private void processRecord(String[] record) {
        try {
            String fullCode = record[0];
            String districtCode = fullCode.substring(2, 5);
            String districtName = extractSecondWord(record[1]);
            String cityCode = fullCode.substring(0, 2);
            String exist = record[2];

            // exist가 "존재"가 아닌 경우 건너뛰기
            if (!"존재".equals(exist)) {
                return;
            }

            // district_code가 000인 경우 건너뛰기
            if ("000".equals(districtCode)) {
                return;
            }

            // districtName이 null인 경우 건너뛰기
            if (districtName == null) {
                return;
            }

            District district = District.builder()
                    .districtCode(districtCode)
                    .districtName(districtName)
                    .cityCode(cityCode)
                    .build();
            districtRepository.save(district);
            log.info("데이터 저장 완료: districtCode={}, districtName={}", districtCode, districtName);
        } catch (Exception e) {
            log.warn("데이터 처리 중 오류 발생. 레코드: {}, 이유: {}", record, e.getMessage());
        }
    }

    private String extractSecondWord(String lawDongName) {
        if (lawDongName == null) return null;
        String[] parts = lawDongName.trim().split(" ");
        return parts.length == 2 ? parts[1] : null;
    }

    public List<DistrictResponseDto> getDistrictList() {
        return districtRepository.findAll().stream()
                .map(district -> new DistrictResponseDto(
                        district.getDistrictId(),
                        district.getDistrictCode(),
                        district.getDistrictName()))
                .collect(Collectors.toList());
    }

    public List<DistrictResponseDto> getDistrictListByCityId(int cityId) {
        // 해당 시도(cityId)와 연결된 시군구를 DistrictRepository에서 조회
        return districtRepository.findByCity_CityId(cityId)
                .stream()
                .map(district -> new DistrictResponseDto(
                        district.getDistrictId(),
                        district.getDistrictCode(),
                        district.getDistrictName()))
                .collect(Collectors.toList());
    }
}
