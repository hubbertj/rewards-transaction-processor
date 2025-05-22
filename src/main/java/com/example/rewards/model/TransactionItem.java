package com.example.rewards.model;

import jakarta.persistence.*;

@Entity
@Table(name = "transaction_item")
public class TransactionItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemName;
    private Double itemPrice;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;
    // getters and setters
}

