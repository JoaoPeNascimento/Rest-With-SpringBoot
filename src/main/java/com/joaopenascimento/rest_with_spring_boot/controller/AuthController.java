package com.joaopenascimento.rest_with_spring_boot.controller;

import com.joaopenascimento.rest_with_spring_boot.dto.auth.AuthRequest;
import com.joaopenascimento.rest_with_spring_boot.dto.auth.AuthResponse;
import com.joaopenascimento.rest_with_spring_boot.dto.auth.RegisterRequest;
import com.joaopenascimento.rest_with_spring_boot.model.User;
import com.joaopenascimento.rest_with_spring_boot.service.JwtService;
import com.joaopenascimento.rest_with_spring_boot.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserService userService;

    public AuthController(AuthenticationManager authManager, JwtService jwtService, UserService userService) {
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );
        UserDetails user = userService.loadUserByUsername(request.username());
        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        User user = userService.register(request.username(), request.password(), "ROLE_USER");
        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
