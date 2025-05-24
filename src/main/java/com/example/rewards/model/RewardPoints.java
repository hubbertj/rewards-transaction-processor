package com.example.rewards.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "rewardpoints")
@Getter
@Setter
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

