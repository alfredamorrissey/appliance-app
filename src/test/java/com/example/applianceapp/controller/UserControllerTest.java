package com.example.applianceapp.controller;

import com.example.applianceapp.entity.User;
import com.example.applianceapp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserRepository userRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUser() {
        // Simulate a user registering with plain text password
        User user = new User(null, "testuser", "plainpass");
        User saved = new User(1L, "testuser", "encodedpass");

        // Mock password encoding
        when(passwordEncoder.encode("plainpass")).thenReturn("encodedpass");

        // Mock saving the user
        when(userRepo.save(any(User.class))).thenReturn(saved);

        // Call the register method
        User result = userController.register(user);

        // Verify the results
        assertEquals(saved.getId(), result.getId());
        assertEquals("testuser", result.getUsername());
        verify(passwordEncoder).encode("plainpass"); // Ensure encoding was called
    }
}
