package com.example.rewards.request;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
public class AuthorizationRequest {

    private String username;

    private String password;

}
