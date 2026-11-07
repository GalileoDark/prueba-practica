package com.econocom.backend.login.infrastructure.in.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginCredentialsInputDto {
    private final String email;
    private final String password;
}
