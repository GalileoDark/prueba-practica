package com.econocom.backend.login.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginCredentials {
    private final String email;
    private final String password;
}
