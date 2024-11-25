package com.sports.backend.disabledcourse.service;

import com.sports.backend.disabledcourse.dao.DisabledCourseRepository;
import com.sports.backend.disabledcourse.domain.DisabledCourse;
import com.sports.backend.disabledcourse.dto.DisabledCourseDto;
import com.sports.backend.disabledfacility.domain.DisabledFacility;
import com.sports.backend.disabledfacility.dto.DisabledFacilityDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DisabledCourseService {
    private final DisabledCourseRepository disabledCourseRepository;
    private final DisabledCourseApiClient disabledCourseApiClient;

    private boolean isDisabledCourseDataLoaded() {
        long count = disabledCourseRepository.count();
        log.info("현재 Facility 테이블에 저장된 데이터 수: {}", count);
        return count > 0;
    }

    @Transactional
    public void fetchAndSaveDisabledCourses(String serviceKey, int numOfRows) {
        if(isDisabledCourseDataLoaded()){
            log.info("DisabledFacility 데이터가 이미 로드되어 있습니다. 저장 과정을 생략합니다.");
            return;
        }

        int pageNo = 1;
        boolean hasMoreData;

        do {
            // 한 페이지의 데이터를 가져옴
            List<DisabledCourseDto> disabledCourses = disabledCourseApiClient.fetchDisabledCourses(serviceKey, pageNo, numOfRows);

            if (disabledCourses.isEmpty()) {
                log.warn("API에서 더 이상 가져올 데이터가 없습니다.");
                break;
            }

            // Facility 엔티티로 변환 후 저장
            List<DisabledCourse> entities = disabledCourses.stream()
                    .map(this::mapToEntity)
                    .toList();

            disabledCourseRepository.saveAll(entities);

            log.info("페이지 {}: {}개의 데이터가 저장되었습니다.", pageNo, entities.size());

            // 다음 페이지로 이동
            pageNo++;
            hasMoreData = disabledCourses.size() == numOfRows; // 현재 페이지에 데이터가 가득 차 있으면 더 가져옴
        } while (hasMoreData);
    }

    private DisabledCourse mapToEntity(DisabledCourseDto dto) {
        return DisabledCourse.builder()
                .busiRegNo(dto.getBusiRegNo())
                .sportNm(dto.getSportNm())
                .courseNm(dto.getCourseNm())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .weekday(dto.getWeekday())
                .courseSetaDesc(dto.getCourseNm())
                .settlAmt(dto.getSettlAmt())
                .build();
    }
}
