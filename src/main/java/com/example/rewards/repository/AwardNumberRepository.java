package com.example.rewards.repository;

import com.example.rewards.model.AwardNumber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AwardNumberRepository extends JpaRepository<AwardNumber, Integer> {
    Optional<AwardNumber> findByNumber(String rewardNumber);
}

