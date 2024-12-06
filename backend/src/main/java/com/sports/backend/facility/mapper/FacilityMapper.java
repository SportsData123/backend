package com.sports.backend.facility.mapper;

import com.sports.backend.city.dao.CityRepository;
import com.sports.backend.city.domain.City;
import com.sports.backend.common.BaseMapper;
import com.sports.backend.district.dao.DistrictRepository;
import com.sports.backend.district.domain.District;
import com.sports.backend.facility.domain.Facility;
import com.sports.backend.facility.dto.FacilityDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * FacilityMapper는 Facility 엔티티와 FacilityDto 간의 변환을 처리합니다.
 * BaseMapper 인터페이스를 구현하여 공통적인 변환 로직을 제공합니다.
 */
@Component
@RequiredArgsConstructor
public class FacilityMapper implements BaseMapper<Facility, FacilityDto> {

    private final CityRepository cityRepository;
    private final DistrictRepository districtRepository;

    /**
     * FacilityDto를 Facility 엔티티로 변환합니다.
     *
     * @param dto 변환할 FacilityDto 객체
     * @return 변환된 Facility 엔티티 객체. 입력이 null인 경우 null 반환.
     */
    @Override
    public Facility toEntity(FacilityDto dto){
        if (dto == null) return null;

        City city = cityRepository.findByCityName(dto.getCityName());
        District district = null;

        if (city != null) {
            district = districtRepository.findByDistrictNameAndCity_CityId(dto.getDistrictName(), city.getCityId());
        }

        return Facility.builder()
                .facilityName(dto.getFacilityName())
                .facilityType(dto.getFacilityType())
                .facilityStatus(dto.getFacilityStatus())
                .roadAddress(dto.getRoadAddress())
                .detailAddress(dto.getDetailAddress())
                .zipCode(dto.getZipCode())
                .longitude(dto.getLongitude())
                .latitude(dto.getLatitude())
                .inOutType(dto.getInOutType())
                .nationFlag(dto.getNationFlag())
                .city(city)
                .district(district)
                .build();
    }

    /**
     * Facility 엔티티를 FacilityDto로 변환합니다.
     *
     * @param entity 변환할 Facility 엔티티 객체
     * @return 변환된 FacilityDto 객체. 입력이 null인 경우 null 반환.
     */
    @Override
    public FacilityDto toDto(Facility entity){
        if (entity == null) {
            return null;
        }

        return FacilityDto.builder()
                .facilityName(entity.getFacilityName())
                .facilityType(entity.getFacilityType())
                .facilityStatus(entity.getFacilityStatus())
                .roadAddress(entity.getRoadAddress())
                .detailAddress(entity.getDetailAddress())
                .zipCode(entity.getZipCode())
                .longitude(entity.getLongitude())
                .latitude(entity.getLatitude())
                .inOutType(entity.getInOutType())
                .nationFlag(entity.getNationFlag())
                .cityName(entity.getCity() != null ? entity.getCity().getCityName() : null)
                .districtName(entity.getDistrict() != null ? entity.getDistrict().getDistrictName() : null)
                .build();
    }
}
