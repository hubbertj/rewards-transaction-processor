package com.example.rewards.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class RewardBalanceResponse {
    private String rewardNumber;
    private double rewardBalance;

}
