package com.example.rewards.security;

import com.example.rewards.model.Role;
import com.example.rewards.model.User;
import com.example.rewards.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomUserDetailsServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private CustomUserDetailsService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadUserByUsername_found() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("pass");
        user.setEnabled(true);
        Role role = new Role();
        role.setName("ROLE_USER");
        user.setRoles(Collections.singleton(role));
        when(userRepository.findByUsername("test")).thenReturn(Optional.of(user));
        UserDetails details = service.loadUserByUsername("test");
        assertEquals("test", details.getUsername());
        assertTrue(details.isEnabled());
    }

    @Test
    void loadUserByUsername_notFound() {
        when(userRepository.findByUsername("notfound")).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class, () -> service.loadUserByUsername("notfound"));
    }
}

