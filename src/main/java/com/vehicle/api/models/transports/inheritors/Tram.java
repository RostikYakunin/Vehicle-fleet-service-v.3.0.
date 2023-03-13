package com.vehicle.api.models.transports.inheritors;

import com.vehicle.api.models.transports.Transport;
import lombok.Data;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Data
@Entity
public class Tram extends Transport {

    @NotBlank(message = "Error, amount of railcars cannot be empty")
    private Integer amountOfRailcar;

    @Override
    public String toString() {
        return super.toString() + ", railcar`s numbers: " + amountOfRailcar;
    }
}
