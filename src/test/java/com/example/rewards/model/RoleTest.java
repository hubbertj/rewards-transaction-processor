package com.example.rewards.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RoleTest {
    @Test
    void testGettersAndSetters() {
        Role role = new Role();
        role.setId(1L);
        role.setName("ROLE_USER");
        assertEquals(1L, role.getId());
        assertEquals("ROLE_USER", role.getName());
    }
}

