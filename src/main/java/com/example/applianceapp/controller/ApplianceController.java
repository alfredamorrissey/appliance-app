package com.example.applianceapp.controller;

import com.example.applianceapp.dto.ACDTO;
import com.example.applianceapp.dto.ApplianceDTO;
import com.example.applianceapp.dto.FanDTO;
import com.example.applianceapp.dto.LightDTO;
import com.example.applianceapp.entity.*;
import com.example.applianceapp.repository.ApplianceRepository;
import com.example.applianceapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/appliances")
public class ApplianceController {

    @Autowired
    private ApplianceRepository applianceRepo;

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/user/{userId}")
    public List<ApplianceDTO> getUserAppliances(@PathVariable Long userId) {
        return applianceRepo.findByUserId(userId).stream()
                .map(appliance -> {
                    if (appliance instanceof Light light) {
                        return new LightDTO(light.getId(), light.getIsOn());
                    } else if (appliance instanceof Fan fan) {
                        return new FanDTO(fan.getId(), fan.getSpeed());
                    } else if (appliance instanceof AC ac) {
                        return new ACDTO(ac.getId(), ac.getTemperature());
                    } else {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .toList();
    }

    @PostMapping("/add/{userId}")
    public Appliance addAppliance(@PathVariable Long userId, @RequestBody Appliance appliance) {
        User user = userRepo.findById(userId).orElseThrow();
        appliance.setUser(user);
        return applianceRepo.save(appliance);
    }

    @PutMapping("/update")
    public void updateAppliance(@RequestBody ApplianceDTO dto) {
        Appliance appliance = applianceRepo.findById(dto.getId()).orElseThrow();

        switch (dto.getType()) {
            case "LIGHT" -> {
                if (appliance instanceof Light light && dto instanceof LightDTO lightDTO) {
                    System.out.println("DTO status: " + lightDTO.getStatus());
                    light.setIsOn(lightDTO.getStatus());
                }
            }
            case "FAN" -> {
                if (appliance instanceof Fan fan && dto instanceof FanDTO fanDTO) {
                    fan.setSpeed(fanDTO.getSpeed());
                }
            }
            case "AC" -> {
                if (appliance instanceof AC ac && dto instanceof ACDTO acDTO) {
                    ac.setTemperature(acDTO.getTemperature());
                }
            }
        }

        applianceRepo.save(appliance);
    }

    @PostMapping("/offAll/{userId}")
    public List<Appliance> turnOffAll(@PathVariable Long userId) {
        List<Appliance> appliances = applianceRepo.findByUserId(userId);
        for (Appliance appliance : appliances) {
            appliance.off();
        }
        return applianceRepo.saveAll(appliances);
    }
}
