package com.econocom.backend.login.infrastructure.in.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthResponseOutputDto {

    private String token;
    private String type;

}
