package com.example.rewards.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();
        ReflectionTestUtils.setField(jwtUtil, "secret", "testSecret");
        ReflectionTestUtils.setField(jwtUtil, "jwtExpirationInMs", 3600000L); // 1 hour
    }

    @Test
    void generateToken_shouldReturnValidToken() {
        String username = "testUser";
        String token = jwtUtil.generateToken(username, null);

        assertNotNull(token);
        assertTrue(token.length() > 0);
    }

    @Test
    void extractUsername_shouldReturnCorrectUsername() {
        String username = "testUser";
        String token = jwtUtil.generateToken(username, null);

        String extractedUsername = jwtUtil.extractUsername(token);
        assertEquals(username, extractedUsername);
    }

    @Test
    void validateToken_shouldReturnTrueForValidToken() {
        String username = "testUser";
        String token = jwtUtil.generateToken(username, null);

        boolean isValid = jwtUtil.validateToken(token, username);
        assertTrue(isValid);
    }

    @Test
    void validateToken_shouldReturnFalseForInvalidToken() {
        String username = "testUser";
        String token = jwtUtil.generateToken(username, null);

        boolean isValid = jwtUtil.validateToken(token, "wrongUser");
        assertFalse(isValid);
    }

    @Test
    void isTokenExpired_shouldReturnTrueForExpiredToken() {
        try (MockedStatic<Jwts> mockedJwts = Mockito.mockStatic(Jwts.class)) {
            Claims claims = Mockito.mock(Claims.class);
            Mockito.when(claims.getExpiration()).thenReturn(new Date(System.currentTimeMillis() - 1000)); // Expired date
            mockedJwts.when(() -> Jwts.parser().setSigningKey("testSecret").parseClaimsJws(Mockito.anyString()).getBody())
                    .thenReturn(claims);

            boolean isExpired = ReflectionTestUtils.invokeMethod(jwtUtil, "isTokenExpired", "dummyToken");
            assertTrue(isExpired);
        }
    }
}