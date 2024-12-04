package com.sports.backend.disabledcourse.mapper;

import com.sports.backend.disabledcourse.domain.DisabledCourse;
import com.sports.backend.disabledcourse.dto.DisabledCourseDto;
import org.springframework.stereotype.Component;

/**
 * `DisabledCourseMapper`는 `DisabledCourseDto`와 `DisabledCourse` 엔티티 간의 변환을 처리하는 클래스입니다.
 * 데이터 전송 객체(DTO)와 엔티티 간 변환 로직을 캡슐화하여 코드 재사용성을 높이고 중복을 방지합니다.
 */
@Component
public class DisabledCourseMapper {

    /**
     * `DisabledCourseDto`를 `DisabledCourse` 엔티티로 변환합니다.
     *
     * @param dto 변환할 `DisabledCourseDto` 객체
     * @return 변환된 `DisabledCourse` 엔티티 객체. 입력이 null인 경우 null 반환.
     */
    public DisabledCourse toEntity(DisabledCourseDto dto) {
        if (dto == null) {
            return null;
        }

        return DisabledCourse.builder()
                .busiRegNo(dto.getBusiRegNo())           // 사업자 등록번호
                .sportNm(dto.getSportNm())               // 종목명
                .courseNm(dto.getCourseNm())             // 강좌명
                .startTime(dto.getStartTime())           // 시작 시간
                .endTime(dto.getEndTime())               // 종료 시간
                .weekday(dto.getWeekday())               // 요일
                .courseSetaDesc(dto.getCourseSetaDesc()) // 상세 설명
                .settlAmt(dto.getSettlAmt())             // 요금 정보
                .roadAddr(dto.getRoadAddr())             // 도로명 주소
                .faciDaddr(dto.getFaciDaddr())           // 상세 주소
                .districtName(dto.getDistrictName())     // 구 이름
                .cityName(dto.getCityName())             // 도시 이름
                .latitude(dto.getLatitude())             // 위도
                .longitude(dto.getLongitude())           // 경도
                .build();
    }

}
