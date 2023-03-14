package com.vehicle.api.models.transports.inheritors;

import com.vehicle.api.models.transports.Transport;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class Bus extends Transport {

    @NotBlank(message = "Error, bus`s type cannot be empty")
    @Column(name = "bus_type")
    private String type;

    @NotBlank(message = "Error, door`s amount cannot be empty")
    @Column(name = "doors_amount")
    private Integer amountOfDoors;

    @Override
    public String toString() {
        return super.toString() + ", transport`s type: " + type + ", door`s numbers: " + amountOfDoors;
    }
}
