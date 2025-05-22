package com.example.rewards.model;

import jakarta.persistence.*;

@Entity
@Table(name = "awardnumbers")
public class AwardNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    // getters and setters
}

