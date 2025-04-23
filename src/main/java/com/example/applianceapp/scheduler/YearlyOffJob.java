package com.example.applianceapp.scheduler;

import com.example.applianceapp.entity.Appliance;
import com.example.applianceapp.repository.ApplianceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class YearlyOffJob {

    @Autowired
    private ApplianceRepository applianceRepo;

    @Scheduled(cron = "0 0 1 1 1 *") // At 01:00 AM on Jan 1
    public void shutOffAllAppliances() {
        List<Appliance> appliances = applianceRepo.findAll();
        for (Appliance appliance : appliances) {
            appliance.off();
        }
        applianceRepo.saveAll(appliances);
        System.out.println("✅ YearlyOffJob ran: All appliances turned off.");
    }
}
