package com.vehicle.api.dto;

import lombok.Data;

@Data
public class TransportDto {
    private Long id;

    private String brandOfTransport;

    private Integer amountOfPassengers;

    private String driverQualificationEnum;

    private String type;

    private Integer amountOfDoors;

    private Integer amountOfRailcar;

    public TransportDto() {
    }

    public TransportDto(String brandOfTransport, Integer amountOfPassengers, String driverQualificationEnum, Integer amountOfRailcar) {
        this.brandOfTransport = brandOfTransport;
        this.amountOfPassengers = amountOfPassengers;
        this.driverQualificationEnum = driverQualificationEnum;
        this.amountOfRailcar = amountOfRailcar;
    }

    public TransportDto(String brandOfTransport, Integer amountOfPassengers, String driverQualificationEnum, String type, Integer amountOfDoors) {
        this.brandOfTransport = brandOfTransport;
        this.amountOfPassengers = amountOfPassengers;
        this.driverQualificationEnum = driverQualificationEnum;
        this.type = type;
        this.amountOfDoors = amountOfDoors;
    }
}
