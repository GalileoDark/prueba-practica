package com.econocom.backend.login.domain.port.out;

public interface TokenPort {
    String generateToken(String email);
}
