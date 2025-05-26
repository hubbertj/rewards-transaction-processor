package com.example.rewards.repository;

import com.example.rewards.model.AwardNumber;
import com.example.rewards.model.RewardPoints;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RewardPointsRepository extends JpaRepository<RewardPoints, Integer> {
    List<RewardPoints> findByAwardNumber(Integer awardNumberId);
    List<RewardPoints> findAllByAwardNumberAndUpdatedAtBetween(Integer awardNumberId,
                                                               LocalDateTime dateFrom,
                                                               LocalDateTime dateTo);
}