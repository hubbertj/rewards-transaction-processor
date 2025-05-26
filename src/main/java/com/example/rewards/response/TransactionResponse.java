package com.example.rewards.response;

import com.example.rewards.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {
    private List<Transaction> transactions;
}
