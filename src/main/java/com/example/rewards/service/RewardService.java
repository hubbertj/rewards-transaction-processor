package com.example.rewards.service;

import com.example.rewards.model.AwardNumber;
import com.example.rewards.model.RewardPoints;
import com.example.rewards.model.Transaction;
import com.example.rewards.repository.AwardNumberRepository;
import com.example.rewards.repository.RewardPointsRepository;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Setter
@Getter
public class RewardService {
    private final AwardNumberRepository awardNumberRepository;
    private final RewardPointsRepository rewardPointsRepository;

    @Autowired
    public RewardService(AwardNumberRepository awardNumberRepository, RewardPointsRepository rewardPointsRepository) {
        this.awardNumberRepository = awardNumberRepository;
        this.rewardPointsRepository = rewardPointsRepository;
    }


    public String getRewardDetails(String rewardId) {
        // Placeholder logic for retrieving reward details
        return "Reward details for ID: " + rewardId;
    }
    public Integer getRewardBalance(String rewardNumber) {
        AwardNumber aw = this.findAwardNumberByNumber(rewardNumber);
        if (aw == null) {
            throw new RuntimeException("Award number not found: " + rewardNumber);
        }
        List<RewardPoints> points = this.rewardPointsRepository.findByAwardNumber(aw.getId());
        if (points.isEmpty()) {
            return 0; // No points found for the award number
        }
        return points.stream()
                .map(RewardPoints::getPoints)
                .reduce(0, Integer::sum);
    }

    public AwardNumber findAwardNumberByNumber(String rewardNumber) {
        return this.awardNumberRepository.findByNumber(rewardNumber).stream()
                .reduce((a, b) -> {
                    throw new RuntimeException("Multiple award numbers found for: " + rewardNumber);
                })
                .orElseThrow(() -> new RuntimeException("Award number not found: " + rewardNumber));
    }

    public void saveRewardPoints(String rewardNumber, double points) {
        AwardNumber awardNumber = this.findAwardNumberByNumber(rewardNumber);
        if (awardNumber == null) {
            throw new RuntimeException("Award number not found: " + rewardNumber);
        }
        RewardPoints rp = RewardPoints.builder()
                .awardNumber(awardNumber.getId())
                .points((int) points)
                .updatedAt(LocalDateTime.now())
                .build();
        this.rewardPointsRepository.save(rp);
    }

    public Integer getRewardBalanceFromDateRange(String rewardNumber, LocalDateTime fromDate, LocalDateTime toDate) {
        AwardNumber awardNumber = this.findAwardNumberByNumber(rewardNumber);
        List<RewardPoints> rewardPoints = this.rewardPointsRepository.findAllByAwardNumberAndUpdatedAtBetween(
                awardNumber.getId(), fromDate, toDate);
        return rewardPoints.stream()
                .map(RewardPoints::getPoints)
                .reduce(0, Integer::sum);
    }
}
