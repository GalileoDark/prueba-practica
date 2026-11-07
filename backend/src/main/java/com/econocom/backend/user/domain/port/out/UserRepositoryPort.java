package com.econocom.backend.user.domain.port.out;

import com.econocom.backend.user.domain.model.User;

import java.util.Optional;

public interface UserRepositoryPort {
    Optional<User> findByEmail(String email);
}
