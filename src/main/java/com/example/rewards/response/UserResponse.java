package com.example.rewards.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class UserResponse {
    private Long id;
    private String username;
    private boolean enabled;
    private List<String> rewardNumbers;
}
