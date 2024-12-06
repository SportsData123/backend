package com.sports.backend.disabledcourse.mapper;

import com.sports.backend.common.BaseMapper;
import com.sports.backend.disabledcourse.domain.DisabledCourse;
import com.sports.backend.disabledcourse.dto.DisabledCourseDto;
import org.springframework.stereotype.Component;

/**
 * `DisabledCourseMapper`는 `DisabledCourseDto`와 `DisabledCourse` 엔티티 간의 변환을 처리하는 클래스입니다.
 * 데이터 전송 객체(DTO)와 엔티티 간 변환 로직을 캡슐화하여 코드 재사용성을 높이고 중복을 방지합니다.
 */
@Component
public class DisabledCourseMapper implements BaseMapper<DisabledCourse, DisabledCourseDto> {

    /**
     * `DisabledCourseDto`를 `DisabledCourse` 엔티티로 변환합니다.
     *
     * @param dto 변환할 `DisabledCourseDto` 객체
     * @return 변환된 `DisabledCourse` 엔티티 객체. 입력이 null인 경우 null 반환.
     */
    @Override
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

    /**
     * - JSON 데이터를 `DisabledCourseDto`로 변환합니다.
     * @param entity JSON 객체
     * @return 변환된 `DisabledCourseDto` 객체
     */
    @Override
    public DisabledCourseDto toDto(DisabledCourse entity) {
        return DisabledCourseDto.builder()
                .busiRegNo(entity.getBusiRegNo())           // 사업자 등록번호
                .sportNm(entity.getSportNm())               // 종목명
                .courseNm(entity.getCourseNm())             // 강좌명
                .startTime(entity.getStartTime())           // 시작 시간
                .endTime(entity.getEndTime())               // 종료 시간
                .weekday(entity.getWeekday())               // 요일
                .courseSetaDesc(entity.getCourseSetaDesc()) // 상세 설명
                .settlAmt(entity.getSettlAmt())             // 요금 정보
                .roadAddr(entity.getRoadAddr())             // 도로명 주소
                .faciDaddr(entity.getFaciDaddr())           // 상세 주소
                .districtName(entity.getDistrictName())     // 구 이름
                .cityName(entity.getCityName())             // 도시 이름
                .latitude(entity.getLatitude())             // 위도
                .longitude(entity.getLongitude())           // 경도
                .build();
    }

}
