package com.example.rewards.repository;

import com.example.rewards.model.AwardNumber;
import com.example.rewards.model.Transaction;
import com.example.rewards.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUserId(Integer userId);
    List<Transaction> findByAwardNumberAndTransactionDateBetween(AwardNumber awardNumber, LocalDateTime fromDate, LocalDateTime toDate);
    List<Transaction>  findAllByUserIdAndTransactionDateBetween(Integer userId, LocalDateTime fromDate, LocalDateTime toDate);
}

