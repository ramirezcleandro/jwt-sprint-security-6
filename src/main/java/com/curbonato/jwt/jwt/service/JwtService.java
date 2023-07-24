package com.curbonato.jwt.jwt.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JwtService {
    String extractUserName(String token);
    String generateToken(UserDetails userDetails);

    String generateToken(Map<String, Object> extraClaims,UserDetails userDetails);
    boolean isTokenValid(String token, UserDetails userDetails);
}
