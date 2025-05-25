package com.example.rewards.controller;

import com.example.rewards.model.Transaction;
import com.example.rewards.response.RewardBalanceResponse;
import com.example.rewards.response.TransactionResponse;
import com.example.rewards.service.TransactionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "transaction", description = "Endpoints for all transaction-related operations")
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController (TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TransactionResponse> getTransactionsByUserId(@PathVariable ("userId") String userId) {
        List<Transaction> transactions = this.transactionService.getTransactionsByUserId(userId);
        TransactionResponse response = new TransactionResponse(transactions);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{userId}/{dateFrom}/{dateTo}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TransactionResponse> getTransactionsByUserId(@PathVariable ("userId") String userId,
                                                                        @PathVariable ("dateFrom") String dateFrom,
                                                                        @PathVariable ("dateTo") String dateTo) {
        List<Transaction> transactions = this.transactionService.getTransactionsByUserIdAndDateRange(userId, dateFrom, dateTo);
        TransactionResponse response = new TransactionResponse(transactions);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
