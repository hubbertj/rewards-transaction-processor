package com.example.rewards.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "login")
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime loginTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    // getters and setters
}

