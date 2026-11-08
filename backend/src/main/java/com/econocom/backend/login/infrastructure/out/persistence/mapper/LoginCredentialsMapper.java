package com.econocom.backend.login.infrastructure.out.persistence.mapper;

import com.econocom.backend.login.domain.model.LoginCredentials;
import com.econocom.backend.login.infrastructure.in.rest.dto.LoginCredentialsInputDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoginCredentialsMapper {

    LoginCredentials toDomain(LoginCredentialsInputDto loginCredentialsInputDto);

}
