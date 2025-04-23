package com.example.applianceapp.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = LightDTO.class, name = "LIGHT"),
        @JsonSubTypes.Type(value = FanDTO.class, name = "FAN"),
        @JsonSubTypes.Type(value = ACDTO.class, name = "AC")
})
public abstract class ApplianceDTO {
    private Long id;
    private String type;

    public ApplianceDTO() {}

    public ApplianceDTO(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    public Long getId() { return id; }
    public String getType() { return type; }

    public void setId(Long id) { this.id = id; }
    public void setType(String type) { this.type = type; }
}
