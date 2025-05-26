package com.example.rewards.service;

import com.example.rewards.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Getter
@Setter
@Service
public class LoginService {
    private final UserService userService;

    @Autowired
    public LoginService(UserService userService) {
        this.userService = userService;
    }

    public User login(String username, String password) {
        Optional<User> user = userService.getUserByUsername(username);
        if (user.isPresent()) {
            User foundUser = user.get();
            if (foundUser.getPassword().equals(password)) {
                return foundUser; // Login successful
            }
        }
        return null; // Login failed
    }

}
