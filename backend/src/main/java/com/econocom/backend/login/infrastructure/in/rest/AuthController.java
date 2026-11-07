package com.econocom.backend.login.infrastructure.in.rest;

import com.econocom.backend.login.domain.model.AuthResponse;
import com.econocom.backend.login.domain.port.in.LoginUseCase;
import com.econocom.backend.login.infrastructure.in.rest.dto.AuthResponseOutputDto;
import com.econocom.backend.login.infrastructure.in.rest.dto.LoginCredentialsInputDto;
import com.econocom.backend.login.infrastructure.out.persistence.mapper.AuthResponseMapper;
import com.econocom.backend.login.infrastructure.out.persistence.mapper.LoginCredentialsMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final LoginUseCase loginUseCase;
    private final LoginCredentialsMapper loginCredentialsMapper;
    private final AuthResponseMapper authResponseMapper;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseOutputDto> login(@RequestBody LoginCredentialsInputDto loginCredentialsInputDto){
        AuthResponse authResponse = loginUseCase.login(
                loginCredentialsMapper.toDomain(loginCredentialsInputDto));
        AuthResponseOutputDto authResponseOutputDto = authResponseMapper.toOutputDto(authResponse);
        return ResponseEntity.ok(authResponseOutputDto);
    }

}
