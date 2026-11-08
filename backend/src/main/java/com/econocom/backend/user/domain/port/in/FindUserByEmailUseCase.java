package com.econocom.backend.user.domain.port.in;

import com.econocom.backend.user.domain.model.User;

public interface FindUserByEmailUseCase {
    User findUserByEmail(String email);
}
