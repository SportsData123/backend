package com.sports.backend.disabledcourse.mapper;

import com.sports.backend.disabledcourse.domain.DisabledCourse;
import com.sports.backend.disabledcourse.dto.DisabledCourseDto;
import org.springframework.stereotype.Component;

@Component
public class DisabledCourseMapper {
    public DisabledCourse toEntity(DisabledCourseDto dto) {
        if (dto == null) {
            return null;
        }
        return DisabledCourse.builder()
                .busiRegNo(dto.getBusiRegNo())
                .sportNm(dto.getSportNm())
                .courseNm(dto.getCourseNm())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .weekday(dto.getWeekday())
                .courseSetaDesc(dto.getCourseSetaDesc())
                .settlAmt(dto.getSettlAmt())
                .build();
    }

    public DisabledCourseDto toDto(DisabledCourse entity) {
        if (entity == null) {
            return null;
        }
        return DisabledCourseDto.builder()
                .busiRegNo(entity.getBusiRegNo())
                .sportNm(entity.getSportNm())
                .courseNm(entity.getCourseNm())
                .startTime(entity.getStartTime())
                .endTime(entity.getEndTime())
                .weekday(entity.getWeekday())
                .courseSetaDesc(entity.getCourseSetaDesc())
                .settlAmt(entity.getSettlAmt())
                .build();
    }
}
