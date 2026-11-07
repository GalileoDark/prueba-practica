package com.econocom.backend.user.infrastructure.out.persistence.adapter;

import com.econocom.backend.user.domain.model.User;
import com.econocom.backend.user.domain.port.out.UserRepositoryPort;
import com.econocom.backend.user.infrastructure.out.persistence.mapper.UserMapper;
import com.econocom.backend.user.infrastructure.out.persistence.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserRepositoryPort {

    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail(email)
                .map(userMapper::toDomain);
    }
}
