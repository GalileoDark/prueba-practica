package com.econocom.backend.login.domain.port.in;

import com.econocom.backend.login.domain.model.AuthResponse;
import com.econocom.backend.login.domain.model.LoginCredentials;

public interface LoginUseCase {
    AuthResponse login(LoginCredentials loginCredentials);
}
