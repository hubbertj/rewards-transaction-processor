package com.example.rewards.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "transaction_item")
@Getter
@Setter
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

