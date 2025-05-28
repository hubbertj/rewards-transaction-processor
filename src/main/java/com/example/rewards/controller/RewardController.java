package com.example.rewards.controller;

import com.example.rewards.model.Transaction;
import com.example.rewards.response.RewardBalanceResponse;
import com.example.rewards.response.RewardBalanceWithTransactionsResponse;
import com.example.rewards.response.RewardResponse;
import com.example.rewards.service.RewardService;
import com.example.rewards.service.TransactionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Tag(name = "Rewards", description = "Endpoints for all reward-related operations")
@RestController
@RequestMapping("/reward")
public class RewardController {


    private final RewardService rewardService;
    private final TransactionService tranactionService;

    @Autowired
    public RewardController(RewardService rewardService, TransactionService transactionService) {
        this.rewardService = rewardService;
        this.tranactionService = transactionService;

    }

    @GetMapping("/balance/{rewardNumber}")
    public ResponseEntity<RewardBalanceResponse> getBalance(@PathVariable ("rewardNumber") String rewardNumber) {
        Integer balance = this.rewardService.getRewardBalance(rewardNumber);
        RewardBalanceResponse response = RewardBalanceResponse.builder()
                .rewardNumber(rewardNumber)
                .rewardBalance(balance.doubleValue())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/balance/{rewardNumber}/range")
    public ResponseEntity<RewardBalanceWithTransactionsResponse> getBalanceWithRange(@PathVariable ("rewardNumber") String rewardNumber,
                                                                                     @RequestParam("fromDate") Date fromDate,
                                                                                     @RequestParam ("toDate") Date toDate) {
        LocalDateTime localFromDate = fromDate.toInstant()
                .atZone(java.time.ZoneId.systemDefault())
                .toLocalDateTime();
        LocalDateTime localToDate = toDate.toInstant()
                .atZone(java.time.ZoneId.systemDefault())
                .toLocalDateTime();
        Integer balance = this.rewardService.getRewardBalanceFromDateRange(rewardNumber, localFromDate, localToDate);
        List<Transaction> transactions = this.tranactionService.getTransactionsFromDateRange(rewardNumber, localFromDate, localToDate);
        RewardBalanceWithTransactionsResponse response = new RewardBalanceWithTransactionsResponse(rewardNumber, balance, transactions);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/user/{rewardNumber}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RewardResponse> getRewardNumber(@PathVariable ("rewardNumber") String rewardNumber){
        RewardResponse response = this.rewardService.getRewardNumber(rewardNumber);
        if (response == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
