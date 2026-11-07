package com.econocom.backend.login.infrastructure.out.security;

import com.econocom.backend.login.domain.port.out.TokenPort;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtTokenAdapter implements TokenPort {

    @Value("${jwt.secret}")
    private String secretKeyString;

    @Value("${jwt.expiration}")
    private long expirationTime;

    @Override
    public String generateToken(String email) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTime);

        return Jwts.builder()
                .subject(email)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(getSigningKey())
                .compact();
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secretKeyString.getBytes(StandardCharsets.UTF_8));
    }
}