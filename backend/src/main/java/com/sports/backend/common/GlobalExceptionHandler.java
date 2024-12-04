package com.sports.backend.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * GlobalExceptionHandler는 애플리케이션 전역에서 발생하는 예외를 처리하는 클래스입니다.
 * 컨트롤러 레이어에서 발생하는 예외를 통합적으로 처리하여 일관된 응답 구조를 제공합니다.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 모든 예외(Exception)에 대한 기본 처리 메서드입니다.
     * 서버에서 처리되지 않은 예외를 처리하고, HTTP 상태 코드 500(내부 서버 오류)와 함께 응답을 반환합니다.
     *
     * @param e 처리되지 않은 예외 객체
     * @return ResponseEntity<ApiResponse<String>> 표준 응답 구조로 예외 메시지 반환
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(500, "서버 오류 발생: " + e.getMessage(), null));
    }

    /**
     * IllegalArgumentException에 대한 처리 메서드입니다.
     * 잘못된 요청으로 인해 발생한 예외를 처리하고, HTTP 상태 코드 400(잘못된 요청)와 함께 응답을 반환합니다.
     *
     * @param e IllegalArgumentException 예외 객체
     * @return ResponseEntity<ApiResponse<String>> 표준 응답 구조로 예외 메시지 반환
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<String>> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse<>(400, "잘못된 요청: " + e.getMessage(), null));
    }
}
