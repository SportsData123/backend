package com.sports.backend.course.mapper;

import com.sports.backend.course.domain.Course;
import com.sports.backend.course.dto.CourseResponseDto;

public class CourseMapper {

    /**
     * DisabledCourse 엔티티를 CourseResponseDto로 변환합니다.
     *
     * @param course DisabledCourse 엔티티
     * @return 변환된 CourseResponseDto
     */
    public static CourseResponseDto toDto(Course course) {
        System.out.println("Mapping Course: " + course);

        return CourseResponseDto.builder()
                .courseId(course.getCourseId())                    // 강좌 ID
                .busiRegNo(course.getBusiRegNo())                  // 사업자 등록번호
                .sportName(course.getSportName())                  // 종목명
                .courseName(course.getCourseName())                // 강좌명
                .startTime(course.getStartTime())                  // 시작 시간
                .endTime(course.getEndTime())                      // 종료 시간
                .weekday(course.getWeekday())                      // 요일
                .description(course.getCourseDesc())               // 강좌 상세 설명
                .isAccessibleForDisabled(course.getIsAccessibleForDisabled()) // 장애인 접근 가능 여부
                .facilSn(course.getFacilSn())                      // 시설 시리얼 넘버
                .fee(course.getSettlAmt())                         // 결제 금액
                .roadAddr(course.getRoadAddr())                    // 도로명 주소
                .faciDaddr(course.getFaciDaddr())                  // 상세 주소
                .districtName(course.getDistrictName())            // 구 이름
                .districtCode(course.getDistrictCode())            // 구 코드
                .districtId(course.getDistrictId())                // 구 ID
                .cityName(course.getCityName())                    // 도시 이름
                .cityCode(course.getCityCode())                    // 도시 코드
                .cityId(course.getCityId())                        // 도시 ID
                .latitude(course.getLatitude())                    // 위도
                .longitude(course.getLongitude())                  // 경도
                .facilityId(course.getFacilityId())                // 시설 ID
                .build();
    }

}
