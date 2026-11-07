package com.econocom.backend.login.application.usecase;

import com.econocom.backend.login.domain.InvalidCredentialsException;
import com.econocom.backend.login.domain.model.AuthResponse;
import com.econocom.backend.login.domain.model.LoginCredentials;
import com.econocom.backend.login.domain.port.in.LoginUseCase;
import com.econocom.backend.login.domain.port.out.TokenPort;
import com.econocom.backend.user.domain.model.User;
import com.econocom.backend.user.domain.port.in.FindUserByEmailUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUseCaseImpl implements LoginUseCase {

    private final TokenPort tokenPort;
    private final FindUserByEmailUseCase findUserByEmailUseCase;
    private static final String INVALID_CREDENTIALS_EXCEPTION_MSG =  "La contraseña introducida no es correcta.";

    @Override
    public AuthResponse login(LoginCredentials loginCredentials) {
        User user = findUserByEmailUseCase.findUserByEmail(loginCredentials.getEmail());
        if (!user.getPassword().equals(loginCredentials.getPassword())){
            throw new InvalidCredentialsException(INVALID_CREDENTIALS_EXCEPTION_MSG);
        }
        String token = tokenPort.generateToken(user.getEmail());

        return new AuthResponse(token, "Bearer");
    }
}
