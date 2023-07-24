package com.curbonato.jwt.jwt.service.imlp;

import com.curbonato.jwt.jwt.dao.request.SignUpRequest;
import com.curbonato.jwt.jwt.dao.request.SigninRequest;
import com.curbonato.jwt.jwt.dao.response.JwtAuthenticationResponse;
import com.curbonato.jwt.jwt.entity.Role;
import com.curbonato.jwt.jwt.entity.RoleEnum;
import com.curbonato.jwt.jwt.entity.User;
import com.curbonato.jwt.jwt.repository.UserRepository;
import com.curbonato.jwt.jwt.service.AuthenticationService;
import com.curbonato.jwt.jwt.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .enabled(true).build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        Map<String, Object> map = user.getRoles().stream()
                .collect(Collectors.toMap( role -> String.valueOf(role.getId()), role -> role));
        var jwt = jwtService.generateToken(map,user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
