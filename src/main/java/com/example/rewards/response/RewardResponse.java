package com.example.rewards.response;

import com.example.rewards.model.User;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RewardResponse {
    String rewardNumber;
    User user;
}
