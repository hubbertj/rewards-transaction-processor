package com.example.rewards.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AuthorizationRequest {
    private String username;
    private String password;
}
