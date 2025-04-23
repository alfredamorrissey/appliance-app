package com.example.applianceapp.dto;

public class FanDTO extends ApplianceDTO {
    private int speed;

    public FanDTO(Long id, int speed) {
        super(id, "FAN");
        this.speed = speed;
    }


    // getters
    public int getSpeed() {
        return this.speed;
    }

    //setters
    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
