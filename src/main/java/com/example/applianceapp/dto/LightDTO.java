package com.example.applianceapp.dto;

public class LightDTO extends ApplianceDTO {
    private boolean status;

    public LightDTO() {
        super();
        setType("LIGHT");
    }

    public LightDTO(Long id, boolean status) {
        super(id, "LIGHT");
        this.status = status;
    }

    // getters
    public boolean getStatus() {
        return this.status;
    }

    // setters
    public void setStatus(boolean status) {
        this.status = status;
    }
}




