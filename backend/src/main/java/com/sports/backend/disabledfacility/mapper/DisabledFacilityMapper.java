package com.sports.backend.disabledfacility.mapper;

import com.sports.backend.common.BaseMapper;
import com.sports.backend.disabledfacility.domain.DisabledFacility;
import com.sports.backend.disabledfacility.dto.DisabledFacilityDto;
import org.springframework.stereotype.Component;

/**
 * `DisabledFacilityMapper`는 `DisabledFacility` 엔티티와 `DisabledFacilityDto` 간의 변환을 담당하는 클래스입니다.
 * 이 클래스는 데이터 전송 및 데이터베이스 저장 시 필요한 객체 간의 매핑을 제공합니다.
 */
@Component
public class DisabledFacilityMapper implements BaseMapper<DisabledFacility, DisabledFacilityDto> {

    /**
     * DTO를 엔티티로 변환
     *
     * @param dto `DisabledFacilityDto` 객체
     * @return 변환된 `DisabledFacility` 엔티티
     * - `dto`가 `null`인 경우, `null`을 반환합니다.
     */
    @Override
    public DisabledFacility toEntity(DisabledFacilityDto dto) {
        if (dto == null) {
            return null;
        }

        return DisabledFacility.builder()
                .facilName(dto.getFacilName())
                .resTelno(dto.getResTelno())
                .mainEventName(dto.getMainEventName())
                .cityCode(dto.getCityCode())
                .cityName(dto.getCityName())
                .districtCode(dto.getDistrictCode())
                .districtName(dto.getDistrictName())
                .roadAddr(dto.getRoadAddr())
                .faciDaddr(dto.getFaciDaddr())
                .build();
    }

    /**
     * 엔티티를 DTO로 변환
     *
     * @param entity `DisabledFacility` 엔티티 객체
     * @return 변환된 `DisabledFacilityDto`
     * - `entity`가 `null`인 경우, `null`을 반환합니다.
     */
    @Override
    public DisabledFacilityDto toDto(DisabledFacility entity) {
        if (entity == null) {
            return null;
        }

        return DisabledFacilityDto.builder()
                .facilName(entity.getFacilName())
                .resTelno(entity.getResTelno())
                .mainEventName(entity.getMainEventName())
                .cityCode(entity.getCityCode())
                .cityName(entity.getCityName())
                .districtCode(entity.getDistrictCode())
                .districtName(entity.getDistrictName())
                .roadAddr(entity.getRoadAddr())
                .faciDaddr(entity.getFaciDaddr())
                .build();
    }
}
