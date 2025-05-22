package com.example.rewards.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RewardPointsTest {
    @Test
    void testGettersAndSetters() {
        RewardPoints points = new RewardPoints();
        points.setId(1L);
        points.setPoints(100);
        User user = new User();
        points.setUser(user);
        assertEquals(1L, points.getId());
        assertEquals(100, points.getPoints());
        assertEquals(user, points.getUser());
    }
}

