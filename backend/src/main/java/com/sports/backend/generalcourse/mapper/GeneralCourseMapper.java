package com.sports.backend.generalcourse.mapper;

import com.sports.backend.common.BaseMapper;
import com.sports.backend.generalcourse.domain.GeneralCourse;
import com.sports.backend.generalcourse.dto.GeneralCourseDto;
import org.springframework.stereotype.Component;

/**
 * `GeneralCourseMapper`는 일반 강좌(GeneralCourse) 엔티티와 DTO 간의 변환을 담당하는 클래스입니다.
 * 데이터를 전송 객체(DTO)와 데이터베이스 엔티티 간에 변환하는 로직을 캡슐화하여 중복 코드를 방지합니다.
 */
@Component
public class GeneralCourseMapper implements BaseMapper<GeneralCourse, GeneralCourseDto> {

    /**
     * GeneralCourseDto를 GeneralCourse 엔티티로 변환합니다.
     *
     * @param dto 변환할 GeneralCourseDto 객체
     * @return GeneralCourse 엔티티 객체. 입력 값이 null인 경우 null을 반환합니다.
     */
    @Override
    public GeneralCourse toEntity(GeneralCourseDto dto){
        return GeneralCourse.builder()
                .busiRegNo(dto.getBusiRegNo())         // 사업자 등록번호
                .sportNm(dto.getSportNm())             // 종목명
                .courseNm(dto.getCourseNm())           // 강좌명
                .startTime(dto.getStartTime())         // 시작 시간
                .endTime(dto.getEndTime())             // 종료 시간
                .weekday(dto.getWeekday())             // 요일 정보
                .courseSetaDesc(dto.getCourseSetaDesc()) // 강좌 상세 설명
                .facilSn(dto.getFacilSn())             // 시설 고유 번호
                .settlAmt(dto.getSettlAmt())           // 결제 금액
                .roadAddr(dto.getRoadAddr())           // 도로명 주소
                .faciDaddr(dto.getFaciDaddr())         // 상세 주소
                .districtName(dto.getDistrictName())   // 구 이름
                .cityName(dto.getCityName())           // 도시 이름
                .latitude(dto.getLatitude())           // 위도
                .longitude(dto.getLongitude())         // 경도
                .build();
    }

    /**
     * GeneralCourse 엔티티를 GeneralCourseDto로 변환합니다.
     *
     * @param entity 변환할 GeneralCourse 엔티티 객체
     * @return GeneralCourseDto 객체. 입력 값이 null인 경우 null을 반환합니다.
     */
    @Override
    public GeneralCourseDto toDto(GeneralCourse entity){
        if (entity == null) {
            return null;
        }

        return GeneralCourseDto.builder()
                .busiRegNo(entity.getBusiRegNo())         // 사업자 등록번호
                .sportNm(entity.getSportNm())             // 종목명
                .courseNm(entity.getCourseNm())           // 강좌명
                .startTime(entity.getStartTime())         // 시작 시간
                .endTime(entity.getEndTime())             // 종료 시간
                .weekday(entity.getWeekday())             // 요일 정보
                .courseSetaDesc(entity.getCourseSetaDesc()) // 강좌 상세 설명
                .facilSn(entity.getFacilSn())             // 시설 고유 번호
                .settlAmt(entity.getSettlAmt())           // 결제 금액
                .roadAddr(entity.getRoadAddr())           // 도로명 주소
                .faciDaddr(entity.getFaciDaddr())         // 상세 주소
                .districtName(entity.getDistrictName())   // 구 이름
                .cityName(entity.getCityName())           // 도시 이름
                .latitude(entity.getLatitude())           // 위도
                .longitude(entity.getLongitude())         // 경도
                .build();
    }
}
