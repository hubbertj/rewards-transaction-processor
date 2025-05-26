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
import io.swagger.v3.oas.annotations.parameters.RequestBody;
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
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private LoginService loginService;

    @Operation(
        summary = "Authenticate user and return JWT token",
        description = "Authenticate with username and password to receive a JWT token."
    )
    @ApiResponse(
        responseCode = "200",
        description = "Authentication successful",
        content = @Content(schema = @Schema(implementation = LoginResponse.class))
    )
    @ApiResponse(
        responseCode = "401",
        description = "Authentication failed",
        content = @Content(schema = @Schema(implementation = LoginResponse.class))
    )
    @PostMapping(path = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<LoginResponse> login(@RequestBody AuthorizationRequest request) {
        try {
            Optional<User> user = loginService.login(request.getUsername(), request.getPassword());
            if (user.isPresent()) {
                String jwt = jwtUtil.generateToken(user.get());
                return ResponseEntity.ok(new LoginResponse(jwt));
            }
            return ResponseEntity.status(401).body(new LoginResponse("Invalid credentials"));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body(new LoginResponse("Authentication failed"));
        }
    }
}

