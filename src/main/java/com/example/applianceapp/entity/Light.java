package com.example.applianceapp.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("LIGHT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Light extends Appliance {
    private Boolean isOn;

    @Override
    public void off() {
        this.isOn = false;
    }

}
