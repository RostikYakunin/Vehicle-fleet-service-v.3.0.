package com.vehicle.api.models.transports.inheritors;

import com.vehicle.api.models.drivers.DriverQualificationEnum;
import com.vehicle.api.models.transports.Transport;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
public class Tram extends Transport {

    @Column(name = "railcar_amount")
    private Integer amountOfRailcar;

    public Tram() {
    }

    public Tram(Long id, String brandOfTransport, Integer amountOfPassengers, DriverQualificationEnum driverQualificationEnum, Integer amountOfRailcar) {
        super(id, brandOfTransport, amountOfPassengers, driverQualificationEnum);
        this.amountOfRailcar = amountOfRailcar;
    }

    public Tram(String brandOfTransport, Integer amountOfPassengers, DriverQualificationEnum driverQualificationEnum, Integer amountOfRailcar) {
        super(brandOfTransport, amountOfPassengers, driverQualificationEnum);
        this.amountOfRailcar = amountOfRailcar;
    }

    @Override
    public String toString() {
        return super.toString() + ", railcar`s numbers: " + amountOfRailcar;
    }
}
