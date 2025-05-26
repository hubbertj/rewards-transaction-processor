package com.example.rewards.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class PurchaseRequest {
    private Integer userId;
    private Double amount;
    private String rewardNumber;
    private List<Integer> items;
}
