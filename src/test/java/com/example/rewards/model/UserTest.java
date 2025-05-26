package com.example.rewards.model;

import org.junit.jupiter.api.Test;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void testGettersAndSetters() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setPassword("pass");
        user.setEnabled(true);
        Role role = new Role();
        role.setName("ROLE_USER");
        user.setRoles(Collections.singleton(role));
        assertEquals(1L, user.getId());
        assertEquals("testuser", user.getUsername());
        assertEquals("pass", user.getPassword());
        assertTrue(user.isEnabled());
        assertEquals(1, user.getRoles().size());
    }
}

