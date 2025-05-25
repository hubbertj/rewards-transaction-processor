package com.example.rewards.controller;

import com.example.rewards.response.RewardBalanceResponse;
import com.example.rewards.response.RewardBalanceWithTransactionsResponse;
import com.example.rewards.service.RewardService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Tag(name = "Rewards", description = "Endpoints for all reward-related operations")
@RestController
@RequestMapping("/reward")
public class RewardController {


    private final RewardService rewardService;

    @Autowired
    public RewardController(RewardService rewardService) {
        this.rewardService = rewardService;

    }

    @GetMapping("/balance")
    public ResponseEntity<RewardBalanceResponse> getBalance(@PathVariable ("awardNumber") String awardNumber) {
        Double balance = this.rewardService.getRewardBalance(awardNumber);
        RewardBalanceResponse response = new RewardBalanceResponse(awardNumber, balance);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/balance/{awardNumber}/{fromDate}/{toDate}")
    public ResponseEntity<RewardBalanceWithTransactionsResponse> getBalanceWithRange(@PathVariable ("awardNumber") String awardNumber,
                                                                                     @PathVariable ("fromDate") String fromDate,
                                                                                     @PathVariable ("toDate") String toDate) {
        // Placeholder logic for date range, can be expanded later
        Double balance = this.rewardService.getRewardBalance(awardNumber);
        RewardBalanceWithTransactionsResponse response = new RewardBalanceWithTransactionsResponse(awardNumber, balance, new ArrayList<>());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
