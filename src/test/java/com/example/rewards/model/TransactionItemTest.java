package com.example.rewards.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TransactionItemTest {
    @Test
    void testGettersAndSetters() {
        TransactionItem item = new TransactionItem();
        item.setId(1L);
        item.setItemName("Item1");
        item.setItemPrice(10.0);
        item.setQuantity(2);
        Transaction transaction = new Transaction();
        item.setTransaction(transaction);
        assertEquals(1L, item.getId());
        assertEquals("Item1", item.getItemName());
        assertEquals(10.0, item.getItemPrice());
        assertEquals(2, item.getQuantity());
        assertEquals(transaction, item.getTransaction());
    }
}

