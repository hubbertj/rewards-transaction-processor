package com.example.rewards.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class LoginTest {
    @Test
    void testGettersAndSetters() {
        Login login = new Login();
        login.setId(1L);
        LocalDateTime now = LocalDateTime.now();
        login.setLoginTime(now);
        User user = new User();
        login.setUser(user);
        assertEquals(1L, login.getId());
        assertEquals(now, login.getLoginTime());
        assertEquals(user, login.getUser());
    }
}

