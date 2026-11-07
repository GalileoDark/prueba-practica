package com.econocom.backend.shared.infrastructure.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class ErrorResponseFactory {

    private ErrorResponseFactory() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    public static ResponseEntity<ErrorResponseDto> create(HttpStatus status, String error, String message) {
        ErrorResponseDto responseBody = new ErrorResponseDto(
                LocalDateTime.now(ZoneId.systemDefault()),
                status.value(),
                error,
                message
        );
        return ResponseEntity.status(status).body(responseBody);
    }
}