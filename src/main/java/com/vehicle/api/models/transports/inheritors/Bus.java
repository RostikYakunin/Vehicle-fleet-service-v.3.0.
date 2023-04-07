package com.vehicle.api.models.transports.inheritors;

import com.vehicle.api.models.drivers.DriverQualificationEnum;
import com.vehicle.api.models.transports.Transport;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class Bus extends Transport {
    public Bus() {
    }


    public Bus(String brandOfTransport, Integer amountOfPassengers, DriverQualificationEnum driverQualificationEnum, String type, Integer amountOfDoors) {
        super(brandOfTransport, amountOfPassengers, driverQualificationEnum);
        this.type = type;
        this.amountOfDoors = amountOfDoors;
    }

    public Bus(Long id, String brandOfTransport, Integer amountOfPassengers, DriverQualificationEnum driverQualificationEnum, String type, Integer amountOfDoors) {
        super(id, brandOfTransport, amountOfPassengers, driverQualificationEnum);
        this.type = type;
        this.amountOfDoors = amountOfDoors;
    }

    @NotBlank(message = "Error, bus`s type cannot be empty")
    @Column(name = "bus_type")
    private String type;

    @Column(name = "doors_amount")
    private Integer amountOfDoors;

    @Override
    public String toString() {
        return super.toString() + ", transport`s type: " + type + ", door`s numbers: " + amountOfDoors;
    }
}
