package com.sports.backend.common;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class ApiResponse<T> {
    private int status;
    private String message;
    private T data;
}
