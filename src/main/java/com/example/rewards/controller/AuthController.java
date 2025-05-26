package com.example.rewards.controller;

import com.example.rewards.model.User;
import com.example.rewards.request.AuthorizationRequest;
import com.example.rewards.response.LoginResponse;
import com.example.rewards.security.JwtUtil;
import com.example.rewards.security.CustomUserDetailsService;
import com.example.rewards.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Tag(name = "Authentication", description = "Endpoints for user authentication")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final LoginService loginService;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(LoginService loginService, JwtUtil jwtUtil) {
        this.loginService = loginService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody AuthorizationRequest request) {
        try {
            Optional<User> user = this.loginService.login(request.getUsername(), request.getPassword());
            if (user.isPresent()) {
                String jwt = this.jwtUtil.generateToken(user.get());
                return ResponseEntity.ok(new LoginResponse(jwt));
            }
            return ResponseEntity.status(401).body(new LoginResponse("Invalid credentials"));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body(new LoginResponse("Authentication failed"));
        }
    }
}

