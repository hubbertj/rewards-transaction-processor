package com.example.rewards.request;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
public class AuthorizationRequest {

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

}
