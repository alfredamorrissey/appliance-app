package com.example.applianceapp.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("FAN")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fan extends Appliance {
    private Integer speed; // 0 = off, 1/2 = speeds

    @Override
    public void off() {
        this.speed = 0;
    }

}
