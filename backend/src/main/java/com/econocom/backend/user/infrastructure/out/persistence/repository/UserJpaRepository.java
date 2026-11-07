package com.econocom.backend.user.infrastructure.out.persistence.repository;

import com.econocom.backend.user.infrastructure.out.persistence.entity.UserJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserJpa, Long> {

    Optional<UserJpa> findByEmail(String email);

}
