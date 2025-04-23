package com.example.applianceapp.entity;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type",
        visible = true // ensures 'type' appears in JSON
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Light.class, name = "LIGHT"),
        @JsonSubTypes.Type(value = Fan.class, name = "FAN"),
        @JsonSubTypes.Type(value = AC.class, name = "AC")
})
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "appliance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Appliance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public abstract void off();
}
