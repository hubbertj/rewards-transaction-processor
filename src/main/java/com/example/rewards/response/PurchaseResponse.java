package com.example.rewards.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class PurchaseResponse {
    private Integer userId;
    private Integer purchaseId;
    private Double amount;
}
