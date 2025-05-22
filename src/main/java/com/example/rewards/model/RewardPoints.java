package com.example.rewards.model;

import jakarta.persistence.*;

@Entity
@Table(name = "rewardpoints")
public class RewardPoints {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer points;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    // getters and setters
}

