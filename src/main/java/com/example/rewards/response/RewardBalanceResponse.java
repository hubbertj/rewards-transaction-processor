package com.example.rewards.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class RewardBalanceResponse {
    private String awardNumber;
    private double rewardBalance;

}
