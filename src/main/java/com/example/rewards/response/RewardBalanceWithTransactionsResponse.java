package com.example.rewards.response;

import com.example.rewards.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class RewardBalanceWithTransactionsResponse {
    private String awardNumber;
    private double rewardBalance;
    private List<Transaction> transactions;
}
