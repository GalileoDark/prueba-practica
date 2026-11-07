package com.econocom.backend.user.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {

    private final Long id;
    private final String email;
    private final String password;

}
