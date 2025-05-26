package com.example.rewards.controller;

import com.example.rewards.request.AuthorizationRequest;
import com.example.rewards.request.PurchaseRequest;
import com.example.rewards.response.LoginResponse;
import com.example.rewards.response.UserResponse;
import com.example.rewards.security.CustomAuthenticationToken;
import com.example.rewards.security.JwtUtil;
import com.example.rewards.security.CustomUserDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @Operation(
        summary = "Authenticate user and return JWT token",
        description = "Authenticate with username and password to receive a JWT token.",
        requestBody = @RequestBody(
            required = true,
            content = @Content(
                schema = @Schema(
                    example = "{\"username\": \"user\", \"password\": \"pass\"}"
                )
            )
        ),
        responses = {
            @ApiResponse(responseCode = "200", description = "JWT token returned", content = @Content(schema = @Schema(example = "{\"token\": \"<jwt>\"}"))),
            @ApiResponse(responseCode = "401", description = "Invalid credentials")
        }
    )
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody AuthorizationRequest request) {
        try {
            authenticationManager.authenticate(new CustomAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid username or password");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        //Get Roles
         List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername(), roles);
        return ResponseEntity.ok(new LoginResponse(jwt));
    }
}

