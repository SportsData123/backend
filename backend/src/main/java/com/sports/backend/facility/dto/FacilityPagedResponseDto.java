package com.sports.backend.facility.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class FacilityPagedResponseDto {
    private int totalCount; // 전체 시설 수
    private int page; // 현재 페이지 번호
    private int size; // 한 페이지당 항목 수
    private int totalPages; // 전체 페이지 수
    private List<FacilityResponseDto> facilities; // 시설 목록
}
