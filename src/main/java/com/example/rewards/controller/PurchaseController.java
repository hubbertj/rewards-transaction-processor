package com.example.rewards.controller;

import com.example.rewards.request.PurchaseRequest;
import com.example.rewards.response.PurchaseResponse;
import com.example.rewards.service.PurchaseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Purchase", description = "Endpoints for users to make purchases and earn rewards")
@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService){
        this.purchaseService = purchaseService;
    }

    @PostMapping("/purchase")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PurchaseResponse> handlePurchase(@RequestBody PurchaseRequest request) {
        System.out.println(request.toString());
        System.out.println(purchaseService.toString());
        PurchaseResponse response = new PurchaseResponse(1232, 456, 78.90);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
