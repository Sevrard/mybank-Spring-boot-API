package com.bank.infrastructure.security;

import com.bank.api.dto.auth.LoginRequest;
import com.bank.api.dto.auth.LoginResponse;
import com.bank.infrastructure.security.jwt.JwtService;
import com.bank.infrastructure.security.userCredentials.UserCredentialsEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.email(),
                                request.password()
                        )
                );
        UserCredentialsEntity principal = (UserCredentialsEntity) authentication.getPrincipal();
        String token = jwtService.generateToken(principal);
        return new LoginResponse(token);
    }
}
