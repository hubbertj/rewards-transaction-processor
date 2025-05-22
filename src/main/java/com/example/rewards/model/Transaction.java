package com.example.rewards.model;

import jakarta.persistence.*;
import java.util.Set;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime transactionDate;
    private Double totalAmount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "transaction")
    private Set<TransactionItem> items;
    // getters and setters
}

