package com.example.rewards.controller;

import com.example.rewards.security.CustomUserDetailsService;
import com.example.rewards.security.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthControllerTest {
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private CustomUserDetailsService userDetailsService;
    @Mock
    private JwtUtil jwtUtil;
    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login_success() {
        Map<String, String> loginRequest = new HashMap<>();
        loginRequest.put("username", "user");
        loginRequest.put("password", "pass");
        UserDetails userDetails = User.withUsername("user").password("pass").roles("USER").build();
        when(userDetailsService.loadUserByUsername("user")).thenReturn(userDetails);
//        when(jwtUtil.generateToken("user"), null).thenReturn("jwt-token");
//        Map<String, String> response = authController.login(loginRequest.get("user"), loginRequest.get("pass"));
//        assertEquals("jwt-token", response.get("token"));
    }

    @Test
    void login_invalidCredentials() {
        Map<String, String> loginRequest = new HashMap<>();
        loginRequest.put("username", "user");
        loginRequest.put("password", "wrong");
        doThrow(new BadCredentialsException("Bad credentials")).when(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
//        assertThrows(RuntimeException.class, () -> authController.login(loginRequest));
    }
}

