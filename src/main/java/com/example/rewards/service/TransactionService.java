package com.example.rewards.service;

import com.example.rewards.model.AwardNumber;
import com.example.rewards.model.Transaction;
import com.example.rewards.model.User;
import com.example.rewards.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Getter
@Setter
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final RewardService rewardService;
    private final UserService userService;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, RewardService rewardService, 
                              UserService userService) {
        this.transactionRepository = transactionRepository;
        this.rewardService = rewardService;
        this.userService = userService;
    }
    
    public List<Transaction> getTransactionsByUserId(Integer userId) {
        Optional<User> user = this.userService.findUserById(userId);
        return user.map(foundUser ->
                this.transactionRepository.findByUserId(userId)
        ).orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
    }
    public List<Transaction> getTransactionsByUserIdAndDateRange(Integer userId, LocalDateTime dateFrom, LocalDateTime dateTo) {
        Optional<User> user = this.userService.findUserById(userId);
        return user.map(foundUser ->
                this.transactionRepository.findAllByUserIdAndTransactionDateBetween(foundUser.getId(), dateFrom, dateTo)
        ).orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
    }

    public Transaction createTransaction(User user, String rewardNumber, List<Integer> items, Double totalAmount) {
        AwardNumber awardNumber = this.rewardService.findAwardNumberByNumber(rewardNumber);
        Transaction t = Transaction.builder()
                .user(user)
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
