package com.sports.backend.course.service;

import com.sports.backend.course.dto.CourseResponseDto;
import com.sports.backend.course.dto.PaginatedCourseResponseDto;
import com.sports.backend.course.mapper.CourseMapper;
import com.sports.backend.disabledcourse.dao.DisabledCourseRepository;
import com.sports.backend.generalcourse.dao.GeneralCourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * CourseService는 강좌 데이터를 처리하는 서비스 클래스입니다.
 * - 장애인 강좌 및 일반 강좌 데이터를 조회 및 필터링합니다.
 * - 강좌 데이터를 병합하고, 페이징 처리하여 반환합니다.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CourseService {

    // 장애인 강좌 데이터를 처리하는 Repository
    private final DisabledCourseRepository disabledCourseRepository;

    // 일반 강좌 데이터를 처리하는 Repository
    private final GeneralCourseRepository generalCourseRepository;

    /**
     * 모든 강좌 데이터를 조회하고, 필터링 조건을 적용한 후 페이징 처리하여 반환합니다.
     *
     * @param page 페이지 번호 (0-based)
     * @param size 페이지 크기
     * @param isAccessibleForDisabled 장애인 접근 가능 여부 필터 (optional)
     * @param weekday 요일 필터 (optional)
     * @param sportName 스포츠 종목 필터 (optional)
     * @param startTime 강좌 시작 시간 필터 (optional)
     * @param endTime 강좌 종료 시간 필터 (optional)
     * @return PaginatedCourseResponseDto 페이징 처리된 강좌 데이터와 메타 정보
     */
    @Transactional(readOnly = true)
    public PaginatedCourseResponseDto getAllCourses(int page, int size, String isAccessibleForDisabled,
                                                    String weekday, String sportName, String startTime, String endTime) {
        {
            List<CourseResponseDto> disabledCourses = disabledCourseRepository.findAll().stream()
                    .map(CourseMapper::toDto)
                    .collect(Collectors.toList());

            // 일반 강좌 데이터를 DTO로 변환
            List<CourseResponseDto> generalCourses = generalCourseRepository.findAll().stream()
                    .map(CourseMapper::toDto)
                    .collect(Collectors.toList());

            // 두 데이터를 합쳐 반환
            List<CourseResponseDto> combinedCourses = disabledCourses;
            combinedCourses.addAll(generalCourses);

            // 필터 조건 적용
            combinedCourses = combinedCourses.stream()
                    .filter(course -> (isAccessibleForDisabled == null || course.getIsAccessibleForDisabled().equals(isAccessibleForDisabled)))
                    .filter(course -> weekday == null || matchesWeekday(weekday, course.getWeekday()))
                    .filter(course -> (sportName == null || course.getSportName().equalsIgnoreCase(sportName)))
                    .filter(course -> (startTime == null ||
                            (course.getStartTime() != null && course.getStartTime().compareTo(LocalTime.parse(startTime)) >= 0)))
                    .filter(course -> (endTime == null ||
                            (course.getEndTime() != null && course.getEndTime().compareTo(LocalTime.parse(endTime)) <= 0)))
                    .distinct()
                    .collect(Collectors.toList());


            // 페이지네이션 처리
            int start = Math.min(page * size, combinedCourses.size());
            int end = Math.min(start + size, combinedCourses.size());
            List<CourseResponseDto> paginatedCourses = combinedCourses.subList(start, end);

            // 페이지네이션 정보 포함 응답
            return PaginatedCourseResponseDto.builder()
                    .totalCount(combinedCourses.size()) // 총 강좌 수
                    .page(page + 1)                    // 0-based에서 1-based로 변환
                    .size(size)                        // 페이지 크기
                    .totalPages((int) Math.ceil((double) combinedCourses.size() / size)) // 총 페이지 수
                    .courses(paginatedCourses)         // 현재 페이지의 강좌 데이터
                    .build();
        }
    }

    /**
     * 사용자 입력 요일과 강좌 요일의 조건을 비교합니다.
     *
     * @param inputWeekday 사용자가 입력한 요일 조건 (예: "1010100")
     * @param courseWeekday 강좌가 진행되는 요일 (예: "1111100")
     * @return boolean 조건에 부합하면 true, 아니면 false
     */
    private boolean matchesWeekday(String inputWeekday, String courseWeekday) {
        if (courseWeekday == null) {
            return false; // 강좌 요일 정보가 없으면 조건 불충족
        }

        for (int i = 0; i < inputWeekday.length(); i++) {
            char inputChar = inputWeekday.charAt(i);
            char courseChar = courseWeekday.charAt(i);

            // 사용자가 원하는 요일 (1인 경우)에 강좌 요일도 1이어야 함
            if (inputChar == '1' && courseChar != '1') {
                return false; // 조건 불충족
            }
        }
        return true; // 모든 조건 만족
    }

}
