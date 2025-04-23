package com.example.applianceapp.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("AC")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AC extends Appliance {
    private Integer temperature;

    @Override
    public void off() {
        this.temperature = null;
    }

}
