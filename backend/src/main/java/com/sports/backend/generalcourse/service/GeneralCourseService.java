package com.sports.backend.generalcourse.service;

import com.sports.backend.generalcourse.dao.GeneralCourseRepository;
import com.sports.backend.generalcourse.domain.GeneralCourse;
import com.sports.backend.generalcourse.dto.GeneralCourseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GeneralCourseService {
    private final GeneralCourseRepository generalCourseRepository;
    private final GeneralCourseApiClient generalCourseApiClient;

    private boolean isGeneralCourseDataLoaded() {
        long count = generalCourseRepository.count();
        log.info("현재 Course 테이블에 저장된 데이터 수: {}", count);
        return count > 0;
    }

    @Transactional
    public void fetchAndSaveGeneralCourses(String serviceKey, int numOfRows) {
        if(isGeneralCourseDataLoaded()){
            log.info("GeneralCourse 데이터가 이미 로드되어 있습니다. 저장 과정을 생략합니다.");
            return;
        }

        int pageNo = 1;
        boolean hasMoreData;

        do {
            // 한 페이지의 데이터를 가져옴
            List<GeneralCourseDto> generalCourses = generalCourseApiClient.fetchGeneralCourses(serviceKey, pageNo, numOfRows);

            if (generalCourses.isEmpty()) {
                log.warn("API에서 더 이상 가져올 데이터가 없습니다.");
                break;
            }

            // Facility 엔티티로 변환 후 저장
            List<GeneralCourse> entities = generalCourses.stream()
                    .map(this::mapToEntity)
                    .toList();

            generalCourseRepository.saveAll(entities);

            log.info("페이지 {}: {}개의 데이터가 저장되었습니다.", pageNo, entities.size());

            // 다음 페이지로 이동
            pageNo++;
            hasMoreData = generalCourses.size() == numOfRows; // 현재 페이지에 데이터가 가득 차 있으면 더 가져옴
        } while (hasMoreData);
    }

    private GeneralCourse mapToEntity(GeneralCourseDto dto) {
        return GeneralCourse.builder()
                .busiRegNo(dto.getBusiRegNo())
                .sportNm(dto.getSportNm())
                .courseNm(dto.getCourseNm())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .weekday(dto.getWeekday())
                .courseSetaDesc(dto.getCourseSetaDesc())
                .facilSn(dto.getFacilSn())
                .settlAmt(dto.getSettlAmt())
                .roadAddr(dto.getRoadAddr())
                .faciDaddr(dto.getFaciDaddr())
                .districtName(dto.getDistrictName())
                .cityName(dto.getCityName())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .build();
    }
}
