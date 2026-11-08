package com.econocom.backend.user.application.usecase;

import com.econocom.backend.user.domain.exceptions.UserNotFoundException;
import com.econocom.backend.user.domain.model.User;
import com.econocom.backend.user.domain.port.in.FindUserByEmailUseCase;
import com.econocom.backend.user.domain.port.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindUserByEmailUseCaseImpl implements FindUserByEmailUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private static final String EMAIL_NOT_FOUND_EXCEPTION_MSG = "El email introducido no está registrado";

    @Override
    public User findUserByEmail(String email) {
        return userRepositoryPort.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(EMAIL_NOT_FOUND_EXCEPTION_MSG));
    }
}
