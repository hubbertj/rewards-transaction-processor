package com.example.rewards.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "login")
@Getter
@Setter
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

