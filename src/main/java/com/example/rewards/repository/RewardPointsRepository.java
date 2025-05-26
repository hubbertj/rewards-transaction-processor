package com.example.rewards.repository;

import com.example.rewards.model.RewardPoints;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RewardPointsRepository extends JpaRepository<RewardPoints, Long> {
    Optional<RewardPoints> findByAwardNumber(String rewardNumber);
}

