package com.example.applianceapp.controller;

import com.example.applianceapp.entity.User;
import com.example.applianceapp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserRepository userRepo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUser() {
        User user = new User(null, "testuser", "password");
        User saved = new User(1L, "testuser", "password");

        when(userRepo.save(user)).thenReturn(saved);

        User result = userController.register(user);

        assertEquals(saved.getId(), result.getId());
        assertEquals("testuser", result.getUsername());
    }

    @Test
    public void testLoginSuccess() {
        User user = new User(1L, "testuser", "password");

        when(userRepo.findByUsername("testuser")).thenReturn(Optional.of(user));

        Optional<User> result = userController.login(new User(null, "testuser", "password"));

        assertTrue(result.isPresent());
    }

    @Test
    public void testLoginFailure() {
        when(userRepo.findByUsername("testuser")).thenReturn(Optional.empty());

        Optional<User> result = userController.login(new User(null, "testuser", "wrong"));

        assertFalse(result.isPresent());
    }
}
