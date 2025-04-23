package com.example.applianceapp.dto;

public class ACDTO extends ApplianceDTO {
    private int temperature;

    public ACDTO(Long id, int temperature) {
        super(id, "AC");
        this.temperature = temperature;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
}
