package com.example.applianceapp.dto;

/**
 * The light can be turned off by toggling a switch to the "off" position.
 */
public class LightDTO extends ApplianceDTO {
    private boolean isOn;

    public LightDTO() {
        super();
        setType("LIGHT");
    }

    public LightDTO(Long id, boolean isOn) {
        super(id, "LIGHT");
        this.isOn = isOn;
    }

    // getters
    public boolean getIsOn() {
        return this.isOn;
    }

    // setters
    public void setIsOn(boolean isOn) {
        this.isOn = isOn;
    }
}




