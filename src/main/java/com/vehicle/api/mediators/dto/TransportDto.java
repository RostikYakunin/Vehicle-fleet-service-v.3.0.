package com.vehicle.api.mediators.dto;

import com.vehicle.api.models.drivers.Driver;
import com.vehicle.api.models.routes.Route;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class TransportDto {
    private Long id;
    private String brandOfTransport;
    private Integer amountOfPassengers;
    private String driverQualificationEnum;
    private String type;
    private Integer amountOfDoors;
    private Integer amountOfRailcar;
    private Set<Driver> drivers = new HashSet<>();
    private Set<Route> routes = new HashSet<>();

    public TransportDto() {
    }

    public TransportDto(String brandOfTransport, Integer amountOfPassengers, String driverQualificationEnum, String type, Integer amountOfDoors) {
        this.brandOfTransport = brandOfTransport;
        this.amountOfPassengers = amountOfPassengers;
        this.driverQualificationEnum = driverQualificationEnum;
        this.type = type;
        this.amountOfDoors = amountOfDoors;
    }

    public TransportDto(String brandOfTransport, Integer amountOfPassengers, String driverQualificationEnum, Integer amountOfRailcar, Set<Driver> drivers, Set<Route> routes) {
        this.brandOfTransport = brandOfTransport;
        this.amountOfPassengers = amountOfPassengers;
        this.driverQualificationEnum = driverQualificationEnum;
        this.amountOfRailcar = amountOfRailcar;
        this.drivers = drivers;
        this.routes = routes;
    }

    public TransportDto(String brandOfTransport, Integer amountOfPassengers, String driverQualificationEnum, String type, Integer amountOfDoors, Set<Driver> drivers, Set<Route> routes) {
        this.brandOfTransport = brandOfTransport;
        this.amountOfPassengers = amountOfPassengers;
        this.driverQualificationEnum = driverQualificationEnum;
        this.type = type;
        this.amountOfDoors = amountOfDoors;
        this.drivers = drivers;
        this.routes = routes;
    }
}
