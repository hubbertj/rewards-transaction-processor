package com.example.rewards.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "transaction", description = "Endpoints for all transaction-related operations")
@RestController
@RequestMapping("/transaction")
public class TransactionController {
}
