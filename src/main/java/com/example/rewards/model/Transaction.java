package com.example.rewards.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime transactionDate;
    private Double totalAmount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "transaction")
    private Set<TransactionItem> items;

    @ManyToOne
    @JoinColumn(name = "awardNumber")
    private AwardNumber awardNumber;

}

