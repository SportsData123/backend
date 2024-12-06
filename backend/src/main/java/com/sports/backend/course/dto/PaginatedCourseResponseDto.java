package com.sports.backend.course.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * PaginatedCourseResponseDto는 강좌 목록에 대한 페이징 응답을 표현하는 DTO(Data Transfer Object) 클래스입니다.
 * 클라이언트 요청에 따라 페이징된 강좌 데이터를 전달하는 데 사용됩니다.
 */
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
