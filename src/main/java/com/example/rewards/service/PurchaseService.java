package com.example.rewards.service;

import com.example.rewards.model.Transaction;
import com.example.rewards.repository.RewardPointsRepository;
import com.example.rewards.response.PurchaseResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Setter
@Getter
public class PurchaseService {

    private final TransactionService transactionService;
    private final RewardService rewardService;

    @Autowired
    public PurchaseService(TransactionService transactionService, RewardService rewardService) {
        this.transactionService = transactionService;
        this.rewardService = rewardService;
    }


    public String processPurchase(String purchaseDetails) {
        // Placeholder logic for processing a purchase
        return "Purchase processed: " + purchaseDetails;
    }

     /* a customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for
        every dollar spent between $50 and $100 in each transaction.
        (e.g. a $120 purchase = 2x$20 + 1 x $50 = 90 points). */
    private double calculateRewardPoints(double amount) {
        double points = 0;

        if (amount > 100) {
            points += (amount - 100) * 2; // 2 points for every dollar over $100
            points += 50; // 1 point for every dollar between $50 and $100
        } else if (amount > 50) {
            points += (amount - 50); // 1 point for every dollar between $50 and $100
        }

        return points;
    }

    public PurchaseResponse createPurchase(String rewardNumber, List<Integer> items, Double totalAmount) {
        Transaction t = this.transactionService.createTransaction(rewardNumber, items, totalAmount);
        this.rewardService.saveRewardPoints(rewardNumber, this.calculateRewardPoints(totalAmount));
        return PurchaseResponse.builder()
                .transactionId(t.getId())
                .build();
    }

}
