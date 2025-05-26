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
    public Double getRewardBalance(String awardNumber) {
        // Placeholder logic for retrieving reward balance
        return 100.0; // Example: returning a fixed balance
    }

    public List<Transaction> getTransactions(String awardNumber, String fromDate, String toDate) {
        // Placeholder logic for retrieving transactions within a date range
        // This can be expanded to fetch actual transactions from a database or other source
//        LocalDateTime date = LocalDateTime.now();
        Long longId = 1L; // Example ID
//        List<Transaction> transaction2 = List.of(new Transaction(longId, LocalDateTime.now(), 50.0), new Transaction("Transaction2", 30.0));
//        return transaction2;
        return null;
    }

    public AwardNumber findAwardNumberByNumber(String rewardNumber) {
        return this.awardNumberRepository.findByNumber(rewardNumber)
                .orElseThrow(() -> new RuntimeException("Award number not found: " + rewardNumber));
    }

    public void saveRewardPoints(String rewardNumber, double v) {
        Optional<RewardPoints> rewardPoints = this.rewardPointsRepository.findByAwardNumber(rewardNumber);
        if(rewardPoints.isPresent()){
            RewardPoints points = rewardPoints.get();
            double balance = (points.getPoints() + v);
            points.setPoints((int) balance);
            this.rewardPointsRepository.save(points);
            return;
        }
    }
}
