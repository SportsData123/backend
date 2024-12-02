package com.sports.backend.course.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class PaginatedCourseResponseDto {
    private long totalCount;             // 총 강좌 수
    private int page;                    // 현재 페이지 번호
    private int size;                    // 페이지 크기
    private int totalPages;              // 총 페이지 수
    private List<CourseResponseDto> courses; // 강좌 데이터
}
