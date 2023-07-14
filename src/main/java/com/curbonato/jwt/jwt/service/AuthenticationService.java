package com.curbonato.jwt.jwt.service;

import com.curbonato.jwt.jwt.dao.request.SignUpRequest;
import com.curbonato.jwt.jwt.dao.request.SigninRequest;
import com.curbonato.jwt.jwt.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}
