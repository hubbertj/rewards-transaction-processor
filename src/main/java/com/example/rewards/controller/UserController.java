package com.example.rewards.controller;

import com.example.rewards.model.AwardNumber;
import com.example.rewards.model.User;
import com.example.rewards.response.UserResponse;
import com.example.rewards.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "User", description = "Endpoints for user management")
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Integer id) {
        User user;
        List<String> rewardNumbers = List.of();
        try {
            user = userService.getUserById(id);
            if (user.getRewardNumber() != null && !user.getRewardNumber().isEmpty()) {
                rewardNumbers = user.getRewardNumber().stream()
                        .map(AwardNumber::getNumber)
                        .toList();
            }
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new UserResponse(user.getId(), user.getUsername(), user.isEnabled(), rewardNumbers));
    }


}
