package com.example.applianceapp.dto;

/***
 * The air conditioner can be turned off by setting the thermostat to the "off" mode.
 * We set status to false, that way we can preserve the temperature setting
 */
public class ACDTO extends ApplianceDTO {
    private int temperature;
    private boolean isOn;

    public ACDTO(Long id, int temperature, boolean isOn) {
        super(id, "AC");
        this.temperature = temperature;
        this.isOn = isOn;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public boolean getIsOn() {
        return this.isOn;
    }

    public void setIsOn(boolean isOn) {
        this.isOn = isOn;
    }
}
