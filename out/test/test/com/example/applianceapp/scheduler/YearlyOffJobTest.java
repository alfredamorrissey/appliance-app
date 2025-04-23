package com.example.applianceapp.scheduler;

import com.example.applianceapp.entity.*;
import com.example.applianceapp.repository.ApplianceRepository;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

import static org.mockito.Mockito.*;

public class YearlyOffJobTest {

    @InjectMocks
    private YearlyOffJob job;

    @Mock
    private ApplianceRepository applianceRepo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testShutOffAllAppliances() {
        Light light = new Light();
        light.setIsOn(true);

        when(applianceRepo.findAll()).thenReturn(List.of(light));

        job.shutOffAllAppliances();

        assertFalse(light.getIsOn());
        verify(applianceRepo).saveAll(any());
    }
}
