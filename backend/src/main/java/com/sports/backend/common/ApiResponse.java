package com.sports.backend.common;

import lombok.*;

/**
 * ApiResponse는 API 요청에 대한 표준 응답 구조를 제공하는 클래스입니다.
 * HTTP 상태 코드, 응답 메시지, 데이터(payload)를 포함합니다.
 *
 * @param <T> 응답 데이터의 타입을 제네릭으로 지원합니다.
 */

@Getter
@Setter
@AllArgsConstructor
public class ApiResponse<T> {
    private int status;
    private String message;
    private T data;
}
