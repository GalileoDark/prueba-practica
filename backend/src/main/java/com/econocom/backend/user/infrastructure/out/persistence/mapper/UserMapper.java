package com.econocom.backend.user.infrastructure.out.persistence.mapper;

import com.econocom.backend.user.domain.model.User;
import com.econocom.backend.user.infrastructure.out.persistence.entity.UserJpa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toDomain(UserJpa userJpa);

}
