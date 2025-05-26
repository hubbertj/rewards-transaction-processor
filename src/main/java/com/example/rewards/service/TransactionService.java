package com.example.rewards.service;

import com.example.rewards.model.AwardNumber;
import com.example.rewards.model.Transaction;
import com.example.rewards.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Getter
@Setter
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final RewardService rewardService;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, RewardService rewardService) {
        this.transactionRepository = transactionRepository;
        this.rewardService = rewardService;
    }
    
    public List<Transaction> getTransactionsByUserId(String userId) {
        // This method should retrieve transactions for a specific user by their userId
        // Currently, it is a placeholder and can be expanded with actual logic later
        return null; // Placeholder return value

    }

    public List<Transaction> getTransactionsByUserIdAndDateRange(String userId, String dateFrom, String dateTo) {
        // This method should retrieve transactions for a specific user by their userId and within a date range
        // Currently, it is a placeholder and can be expanded with actual logic later
        return null; // Placeholder return value
    }

    public Transaction createTransaction(String rewardNumber, List<Integer> items, Double totalAmount) {
        AwardNumber awardNumber = this.rewardService.findAwardNumberByNumber(rewardNumber);
        Transaction t = Transaction.builder()
                .awardNumber(awardNumber)
//                .items(items)
                .totalAmount(totalAmount)
                .transactionDate(LocalDateTime.now())
                .build();
        return this.transactionRepository.save(t);
    }

    public List<Transaction> getTransactionsFromDateRange(String rewardNumber, LocalDateTime fromDate, LocalDateTime toDate) {
        AwardNumber awardNumber = this.rewardService.findAwardNumberByNumber(rewardNumber);
        if (awardNumber == null) {
            throw new RuntimeException("Award number not found: " + rewardNumber);
        }
        return this.transactionRepository.findByAwardNumberAndTransactionDateBetween(awardNumber, fromDate, toDate);
    }
}
