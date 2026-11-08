package com.econocom.backend.shared.infrastructure.rest.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ErrorResponseDto {

    private final LocalDateTime timestamp;
    private final int status;
    private final String error;
    private final String message;

}