package com.sports.backend.generalfacility.mapper;

import com.sports.backend.common.BaseMapper;
import com.sports.backend.generalfacility.domain.GeneralFacility;
import com.sports.backend.generalfacility.dto.GeneralFacilityDto;
import org.springframework.stereotype.Component;

/**
 * `GeneralFacilityMapper`는 `GeneralFacility` 엔티티와 `GeneralFacilityDto` 간의 변환을 처리하는 클래스입니다.
 * 데이터 전송 객체(DTO)와 엔티티 간의 변환 로직을 캡슐화하여 중복 코드 제거와 유지보수성을 향상시킵니다.
 */
@Component
public class GeneralFacilityMapper implements BaseMapper<GeneralFacility, GeneralFacilityDto> {

    /**
     * `GeneralFacilityDto`를 `GeneralFacility` 엔티티로 변환합니다.
     *
     * @param dto 변환할 `GeneralFacilityDto` 객체
     * @return 변환된 `GeneralFacility` 엔티티 객체
     */
    @Override
    public GeneralFacility toEntity(GeneralFacilityDto dto){
        return GeneralFacility.builder()
                .facilName(dto.getFacilName())          // 시설 이름
                .resTelno(dto.getResTelno())            // 시설 연락처
                .mainEventName(dto.getMainEventName())  // 주요 이벤트 이름
                .cityCode(dto.getCityCode())            // 도시 코드
                .cityName(dto.getCityName())            // 도시 이름
                .districtCode(dto.getDistrictCode())    // 구 코드
                .districtName(dto.getDistrictName())    // 구 이름
                .brno(dto.getBrno())                    // 사업자 등록번호
                .facilSn(dto.getFacilSn())              // 시설 고유번호
                .roadAddr(dto.getRoadAddr())            // 도로명 주소
                .faciDaddr(dto.getFaciDaddr())          // 상세 주소
                .build();
    }

    /**
     * `GeneralFacility` 엔티티를 `GeneralFacilityDto`로 변환합니다.
     *
     * @param entity 변환할 `GeneralFacility` 엔티티 객체
     * @return 변환된 `GeneralFacilityDto` 객체
     */
    @Override
    public GeneralFacilityDto toDto(GeneralFacility entity){
        return GeneralFacilityDto.builder()
                .facilName(entity.getFacilName())          // 시설 이름
                .resTelno(entity.getResTelno())            // 시설 연락처
                .mainEventName(entity.getMainEventName())  // 주요 이벤트 이름
                .cityCode(entity.getCityCode())            // 도시 코드
                .cityName(entity.getCityName())            // 도시 이름
                .districtCode(entity.getDistrictCode())    // 구 코드
                .districtName(entity.getDistrictName())    // 구 이름
                .brno(entity.getBrno())                    // 사업자 등록번호
                .facilSn(entity.getFacilSn())              // 시설 고유번호
                .roadAddr(entity.getRoadAddr())            // 도로명 주소
                .faciDaddr(entity.getFaciDaddr())          // 상세 주소
                .build();
    }
}
