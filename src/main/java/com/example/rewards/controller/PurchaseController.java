package com.example.rewards.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Purchase", description = "Endpoints for users to make purchases and earn rewards")
@RestController
@RequestMapping("/purchase")
public class PurchaseController {
}
