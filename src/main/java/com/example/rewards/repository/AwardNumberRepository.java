package com.example.rewards.repository;

import com.example.rewards.model.AwardNumber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AwardNumberRepository extends JpaRepository<AwardNumber, Integer> {
    List<AwardNumber> findByNumber(String number);
}

