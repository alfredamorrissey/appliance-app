package com.example.applianceapp.controller;

import com.example.applianceapp.entity.*;
import com.example.applianceapp.repository.ApplianceRepository;
import com.example.applianceapp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ApplianceControllerTest {

    @InjectMocks
    private ApplianceController applianceController;

    @Mock
    private ApplianceRepository applianceRepo;

    @Mock
    private UserRepository userRepo;

    private User mockUser;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockUser = new User(1L, "user", "pass");
    }

    @Test
    public void testAddAppliance() {
        Light light = new Light();
        light.setIsOn(true);

        when(userRepo.findById(1L)).thenReturn(Optional.of(mockUser));
        when(applianceRepo.save(any())).thenAnswer(i -> i.getArguments()[0]);

        Appliance result = applianceController.addAppliance(1L, light);

        assertTrue(result instanceof Light);
        assertEquals(mockUser, result.getUser());
    }

    @Test
    public void testTurnOffAll() {
        Fan fan = new Fan();
        fan.setSpeed(2);
        fan.setUser(mockUser);

        AC ac = new AC();
        ac.setTemperature(22);
        ac.setUser(mockUser);

        when(applianceRepo.findByUserId(1L)).thenReturn(List.of(fan, ac));
        when(applianceRepo.saveAll(any())).thenAnswer(i -> i.getArguments()[0]);

        List<Appliance> result = applianceController.turnOffAll(1L);

        assertEquals(0, ((Fan) result.get(0)).getSpeed());
        assertNull(((AC) result.get(1)).getTemperature());
    }
}
