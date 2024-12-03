package com.sports.backend.disabledfacility.mapper;

import com.sports.backend.disabledfacility.domain.DisabledFacility;
import com.sports.backend.disabledfacility.dto.DisabledFacilityDto;
import org.springframework.stereotype.Component;

@Component
public class DisabledFacilityMapper {
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
