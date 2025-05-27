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
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Tag(name = "Transactions", description = "Endpoints for all transaction-related operations")
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
    public ResponseEntity<TransactionResponse> getTransactionsByUserId(@PathVariable ("userId") Integer userId) {
        List<Transaction> transactions = this.transactionService.getTransactionsByUserId(userId);
        TransactionResponse response = new TransactionResponse(transactions);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{userId}/range")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TransactionResponse> getTransactionsByUserId(@PathVariable ("userId") Integer userId,
                                                                       @RequestParam("fromDate") Date fromDate,
                                                                       @RequestParam ("toDate") Date toDate) {

        LocalDateTime localFromDate = fromDate.toInstant()
                .atZone(java.time.ZoneId.systemDefault())
                .toLocalDateTime();
        LocalDateTime localToDate = toDate.toInstant()
                .atZone(java.time.ZoneId.systemDefault())
                .toLocalDateTime();
        List<Transaction> transactions = this.transactionService.getTransactionsByUserIdAndDateRange(userId, localFromDate, localToDate);
        TransactionResponse response = new TransactionResponse(transactions);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
