package com.example.rewards.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "rewardpoints")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RewardPoints {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer points;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Integer awardNumber;

    private LocalDateTime updatedAt;

}

