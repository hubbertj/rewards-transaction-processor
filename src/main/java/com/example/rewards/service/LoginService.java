package com.example.rewards.service;

import com.example.rewards.model.Login;
import com.example.rewards.model.User;
import com.example.rewards.repository.LoginRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@Setter
@Service
public class LoginService {
    private final UserService userService;
    private final LoginRepository loginRepository;

    @Autowired
    public LoginService(UserService userService, LoginRepository loginRepository) {
        this.userService = userService;
        this.loginRepository = loginRepository;
    }

    public Optional<User> login(String username, String password) {
        Optional<User> user = userService.getUserByUsername(username);
        if (user.isPresent()) {
            User foundUser = user.get();
            if (foundUser.getPassword().equals(password)) {
                this.loginRepository.save(new Login(null, LocalDateTime.now(), foundUser));
                return Optional.of(foundUser); // Login successful
            }
            throw new BadCredentialsException("Invalid username or password");
        }
        return Optional.empty(); // Login failed
    }
}