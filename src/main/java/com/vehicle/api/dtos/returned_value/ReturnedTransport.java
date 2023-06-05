package com.vehicle.api.dtos.returned_value;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ReturnedTransport {
    private Long id;

    private String brandOfTransport;

    private Integer amountOfPassengers;

    private String driverQualificationEnum;

    private String type;

    private Integer amountOfDoors;

    private Integer amountOfRailcar;

    private Set<Long> routesId = new HashSet<>();

    public ReturnedTransport() {
    }

    public ReturnedTransport(String brandOfTransport, Integer amountOfPassengers, String driverQualificationEnum, String type, Integer amountOfDoors) {
        this.brandOfTransport = brandOfTransport;
        this.amountOfPassengers = amountOfPassengers;
        this.driverQualificationEnum = driverQualificationEnum;
        this.type = type;
        this.amountOfDoors = amountOfDoors;
    }

    public ReturnedTransport(String brandOfTransport, Integer amountOfPassengers, String driverQualificationEnum, Integer amountOfRailcar) {
        this.brandOfTransport = brandOfTransport;
        this.amountOfPassengers = amountOfPassengers;
        this.driverQualificationEnum = driverQualificationEnum;
        this.amountOfRailcar = amountOfRailcar;
    }


    private Set<Long> driversId = new HashSet<>();

}
