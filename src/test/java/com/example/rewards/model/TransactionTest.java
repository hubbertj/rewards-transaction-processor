package com.example.rewards.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {
    @Test
    void testGettersAndSetters() {
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setTotalAmount(100.0);
        User user = new User();
        transaction.setUser(user);
        TransactionItem item = new TransactionItem();
        transaction.setItems(Collections.singleton(item));
        assertEquals(1L, transaction.getId());
        assertEquals(100.0, transaction.getTotalAmount());
        assertEquals(user, transaction.getUser());
        assertEquals(1, transaction.getItems().size());
    }
}

