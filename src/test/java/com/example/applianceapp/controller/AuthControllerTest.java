package com.example.applianceapp.controller;

import com.example.applianceapp.entity.User;
import com.example.applianceapp.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthControllerTest { // Changed from UserControllerTest

    @InjectMocks
    private AuthController authController; // Changed from UserController to AuthController

    @Mock
    private UserRepository userRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testLoginSuccess() {
        // Simulate a successful login
        User storedUser = new User(1L, "testuser", "encodedpass");
        Map<String, String> loginData = new HashMap<>();
        loginData.put("username", "testuser");
        loginData.put("password", "password");

        // Mock the user lookup and password match
        when(userRepo.findByUsername("testuser")).thenReturn(Optional.of(storedUser));
        when(passwordEncoder.matches("password", "encodedpass")).thenReturn(true);

        // Call the login method
        ResponseEntity<User> response = authController.login(loginData, mock(HttpServletRequest.class), mock(HttpServletResponse.class));

        // Verify the results
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("testuser", response.getBody().getUsername());
    }

    @Test
    public void testLoginFailure() {
        // Simulate a failed login attempt
        User storedUser = new User(1L, "testuser", "encodedpass");
        Map<String, String> loginData = new HashMap<>();
        loginData.put("username", "testuser");
        loginData.put("password", "wrongpass");

        // Mock the user lookup and password mismatch
        when(userRepo.findByUsername("testuser")).thenReturn(Optional.of(storedUser));
        when(passwordEncoder.matches("wrongpass", "encodedpass")).thenReturn(false);

        // Call the login method
        ResponseEntity<User> response = authController.login(loginData, mock(HttpServletRequest.class), mock(HttpServletResponse.class));

        // Verify the results
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    public void testLoginUserNotFound() {
        // Simulate a login attempt with a username not found in the database
        Map<String, String> loginData = new HashMap<>();
        loginData.put("username", "nonexistentuser");
        loginData.put("password", "somepass");

        // Mock the user lookup to return empty (user not found)
        when(userRepo.findByUsername("nonexistentuser")).thenReturn(Optional.empty());

        // Call the login method
        ResponseEntity<User> response = authController.login(loginData, mock(HttpServletRequest.class), mock(HttpServletResponse.class));

        // Verify the results
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }
}
