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

    @Test
    public void testLoginSuccess() {
        // Simulate a successful login
        User storedUser = new User(1L, "testuser", "encodedpass");
        User loginAttempt = new User(null, "testuser", "plainpass");

        // Mock the user lookup and password match
        when(userRepo.findByUsername("testuser")).thenReturn(Optional.of(storedUser));
        when(passwordEncoder.matches("plainpass", "encodedpass")).thenReturn(true);

        // Call the login method
        ResponseEntity<User> response = userController.login(loginAttempt);

        // Verify the results
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("testuser", response.getBody().getUsername());
    }

    @Test
    public void testLoginFailure() {
        // Simulate a failed login attempt
        User loginAttempt = new User(null, "testuser", "wrongpass");
        User storedUser = new User(1L, "testuser", "encodedpass");

        // Mock the user lookup and password mismatch
        when(userRepo.findByUsername("testuser")).thenReturn(Optional.of(storedUser));
        when(passwordEncoder.matches("wrongpass", "encodedpass")).thenReturn(false);

        // Call the login method
        ResponseEntity<User> response = userController.login(loginAttempt);

        // Verify the results
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testLoginUserNotFound() {
        // Simulate a login attempt with a username not found in the database
        User loginAttempt = new User(null, "nonexistentuser", "somepass");

        // Mock the user lookup to return empty (user not found)
        when(userRepo.findByUsername("nonexistentuser")).thenReturn(Optional.empty());

        // Call the login method
        ResponseEntity<User> response = userController.login(loginAttempt);

        // Verify the results
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertNull(response.getBody());
    }
}
