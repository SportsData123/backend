package com.sports.backend.course.mapper;

import com.sports.backend.course.domain.Course;
import com.sports.backend.course.dto.CourseResponseDto;
import com.sports.backend.disabledcourse.domain.DisabledCourse;
import com.sports.backend.generalcourse.domain.GeneralCourse;

public class CourseMapper {

    /**
     * DisabledCourse 엔티티를 CourseResponseDto로 변환합니다.
     *
     * @param course DisabledCourse 엔티티
     * @return 변환된 CourseResponseDto
     */
    public static CourseResponseDto toDto(Course course) {
        return CourseResponseDto.builder()
                .courseId(course.getCourseId())
                .busiRegNo(course.getBusiRegNo())              // 사업자 등록번호
                .sportName(course.getSport().getSportName())               // 종목명
                .courseName(course.getCourseName())             // 강좌명
                .startTime(course.getStartTime())             // 시작 시간
                .endTime(course.getEndTime())                 // 종료 시간
                .weekday(course.getWeekday())                 // 요일
                .description(course.getCourseSetaDesc())      // 상세 설명
                .fee(course.getSettlAmt())                    // 요금
                .isAccessibleForDisabled(course.getIsAccessibleForDisabled())                 // 장애인 접근 가능 여부
                .cityName(course.getCityName())               // 도시 이름
                .districtName(course.getDistrictName())       // 구 이름
                .roadAddr(course.getRoadAddr())               // 도로명 주소
                .faciDaddr(course.getFaciDaddr())             // 상세 주소
                .latitude(course.getLatitude())               // 위도
                .longitude(course.getLongitude())             // 경도
                .build();
    }

}
