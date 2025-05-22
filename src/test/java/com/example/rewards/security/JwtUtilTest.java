package com.example.rewards.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {
    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();
        ReflectionTestUtils.setField(jwtUtil, "secret", "testsecret");
        ReflectionTestUtils.setField(jwtUtil, "jwtExpirationInMs", 10000L);
    }

    @Test
    void generateAndValidateToken() {
        String token = jwtUtil.generateToken("testuser");
        assertNotNull(token);
        String username = jwtUtil.extractUsername(token);
        assertEquals("testuser", username);
        assertTrue(jwtUtil.validateToken(token, "testuser"));
    }

    @Test
    void tokenShouldBeInvalidForWrongUser() {
        String token = jwtUtil.generateToken("testuser");
        assertFalse(jwtUtil.validateToken(token, "otheruser"));
    }
}

