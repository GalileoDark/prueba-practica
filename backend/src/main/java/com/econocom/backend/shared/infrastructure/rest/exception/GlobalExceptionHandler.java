package com.econocom.backend.shared.infrastructure.rest.exception;

import com.econocom.backend.login.domain.InvalidCredentialsException;
import com.econocom.backend.user.domain.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleUserNotFound(UserNotFoundException ex) {
        return ErrorResponseFactory.create(HttpStatus.NOT_FOUND, "Not Found", ex.getMessage());
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidCredentials(InvalidCredentialsException ex) {
        return ErrorResponseFactory.create(HttpStatus.UNAUTHORIZED, "Unauthorized", ex.getMessage());
    }
}