package com.example.rewards.repository;

import com.example.rewards.model.RewardPoints;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RewardPointsRepository extends JpaRepository<RewardPoints, Long> {
}

