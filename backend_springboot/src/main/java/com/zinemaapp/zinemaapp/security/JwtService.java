package com.zinemaapp.zinemaapp.security;

import com.zinemaapp.zinemaapp.infrastructure.persistence.entity.UserEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret.key}")
    private String SECRET_KEY;

    public String generateToken(UserEntity userEntity) {
        return Jwts.builder().subject(userEntity.getEmail()).issuedAt(new Date())
                .expiration(Date.from(Instant.now().plus(Duration.ofDays(365)))).signWith(getSigningKey()).compact();
    }

    public String getSubject(String token) {
        return Jwts.parser().verifyWith((SecretKey) getSigningKey()).build().parseSignedClaims(token).getPayload().getSubject();
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
}
