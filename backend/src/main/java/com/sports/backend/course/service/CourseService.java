package com.sports.backend.course.service;

import com.sports.backend.course.dto.CourseResponseDto;
import com.sports.backend.course.dto.PaginatedCourseResponseDto;
import com.sports.backend.disabledcourse.dao.DisabledCourseRepository;
import com.sports.backend.generalcourse.dao.GeneralCourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseService {
    private final DisabledCourseRepository disabledCourseRepository;
    private final GeneralCourseRepository generalCourseRepository;

    @Transactional(readOnly = true)
    public PaginatedCourseResponseDto getAllCourses(int page, int size, String isAccessibleForDisabled,
                                                    String weekday, String sportName, String startTime, String endTime) {
        {
            // 장애인 강좌 데이터
            List<CourseResponseDto> disabledCourses = disabledCourseRepository.findAll().stream()
                    .map(course -> CourseResponseDto.builder()
                            .courseId(course.getDisabledCourseId())
                            .busiRegNo(course.getBusiRegNo())
                            .sportName(course.getSportNm())
                            .courseName(course.getCourseNm())
                            .startTime(course.getStartTime())
                            .endTime(course.getEndTime())
                            .weekday(course.getWeekday())
                            .description(course.getCourseSetaDesc())
                            .fee(course.getSettlAmt())
                            .isAccessibleForDisabled("Y")
                            .cityName(course.getCityName())          // 도시 이름
                            .districtName(course.getDistrictName())  // 구 이름
                            .roadAddr(course.getRoadAddr())          // 도로명 주소
                            .faciDaddr(course.getFaciDaddr())        // 상세 주소
                            .latitude(course.getLatitude())          // 위도
                            .longitude(course.getLongitude())        // 경도
                            .build())
                    .collect(Collectors.toList());

            // 일반 강좌 데이터
            List<CourseResponseDto> generalCourses = generalCourseRepository.findAll().stream()
                    .map(course -> CourseResponseDto.builder()
                            .courseId(course.getGeneralCourseId())
                            .busiRegNo(course.getBusiRegNo())
                            .sportName(course.getSportNm())
                            .courseName(course.getCourseNm())
                            .startTime(course.getStartTime())
                            .endTime(course.getEndTime())
                            .weekday(course.getWeekday())
                            .description(course.getCourseSetaDesc())
                            .fee(null)
                            .isAccessibleForDisabled("N")
                            .cityName(course.getCityName())          // 도시 이름
                            .districtName(course.getDistrictName())  // 구 이름
                            .roadAddr(course.getRoadAddr())          // 도로명 주소
                            .faciDaddr(course.getFaciDaddr())        // 상세 주소
                            .latitude(course.getLatitude())          // 위도
                            .longitude(course.getLongitude())        // 경도
                            .build())
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
                    .totalCount(combinedCourses.size())
                    .page(page + 1) // 0-based에서 1-based로 변환
                    .size(size)
                    .totalPages((int) Math.ceil((double) combinedCourses.size() / size))
                    .courses(paginatedCourses)
                    .build();
        }
    }

    private boolean matchesWeekday(String inputWeekday, String courseWeekday) {
        if (courseWeekday == null) {
            return false; // courseWeekday가 null이면 조건 불충족
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
