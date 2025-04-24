package com.example.applianceapp.dto;

/**
 * The Fan can have speed 0 to 3. 0 means the power is off.
 */
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
