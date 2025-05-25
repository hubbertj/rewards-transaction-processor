package com.example.rewards.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Rewards", description = "Endpoints for all reward-related operations")
@RestController
@RequestMapping("/reward")
public class RewardController {
}
