package com.econocom.backend.login.infrastructure.out.persistence.mapper;

import com.econocom.backend.login.domain.model.AuthResponse;
import com.econocom.backend.login.infrastructure.in.rest.dto.AuthResponseOutputDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthResponseMapper {

    AuthResponseOutputDto toOutputDto(AuthResponse authResponse);

}
