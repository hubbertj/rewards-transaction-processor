package com.example.rewards.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AwardNumberTest {
    @Test
    void testGettersAndSetters() {
        AwardNumber awardNumber = new AwardNumber();
        awardNumber.setId(1L);
        awardNumber.setNumber("A123");
        assertEquals(1L, awardNumber.getId());
        assertEquals("A123", awardNumber.getNumber());
    }
}

