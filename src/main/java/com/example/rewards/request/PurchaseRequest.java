package com.example.rewards.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class PurchaseRequest {
    private Integer userId;
    private Integer purchaseId;
    private Double amount;

}
