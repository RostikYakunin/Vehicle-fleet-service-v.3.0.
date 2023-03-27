package com.vehicle.api.dto.returned_value;

import com.vehicle.api.models.drivers.Driver;
import com.vehicle.api.models.drivers.DriverQualificationEnum;
import com.vehicle.api.models.routes.Route;
import com.vehicle.api.models.transports.Transport;
import com.vehicle.api.models.transports.inheritors.Bus;
import com.vehicle.api.models.transports.inheritors.Tram;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class Converter {

    public static ReturnedRoute convertToReturnedRoute(Route route) {
        ReturnedRoute returnedRoute = new ReturnedRoute();
        returnedRoute.setId(route.getId());
        returnedRoute.setStartOfWay(route.getStartOfWay());
        returnedRoute.setEndOfWay(route.getEndOfWay());

        Set<Transport> transports = route.getTransports();
        if (!transports.isEmpty()) {
            returnedRoute.setTransportsId(transports.stream().map(Transport::getId).collect(Collectors.toSet()));
        }

        Set<Driver> drivers = route.getDrivers();
        if (!drivers.isEmpty()) {
            returnedRoute.setDriversId(drivers.stream().map(Driver::getId).collect(Collectors.toSet()));
        }
        return returnedRoute;
    }

    public static ReturnedDriver convertToReturnedDriver(Driver driver) {
        ReturnedDriver returnedDriver = new ReturnedDriver();

        returnedDriver.setId(driver.getId());
        returnedDriver.setNameOfDriver(driver.getNameOfDriver());
        returnedDriver.setSurnameOfDriver(driver.getSurnameOfDriver());
        returnedDriver.setPhoneNumber(driver.getPhoneNumber());
        returnedDriver.setQualificationEnum(driver.getQualificationEnum().name());

        if (driver.getRoute() != null) {
            returnedDriver.setRouteId(driver.getRoute().getId());
        }

        Set<Transport> transports = driver.getTransport();
        if (!transports.isEmpty()) {
            Set<Long> collected = transports.stream().map(Transport::getId).collect(Collectors.toSet());
            returnedDriver.setTransportId(new HashSet<>(collected));
        }

        return returnedDriver;
    }

    public static ReturnedTransport convertToReturnedTransport (Transport transport) {
        ReturnedTransport returnedTransport = new ReturnedTransport();

        returnedTransport.setId(transport.getId());
        returnedTransport.setBrandOfTransport(transport.getBrandOfTransport());
        returnedTransport.setAmountOfPassengers(transport.getAmountOfPassengers());
        returnedTransport.setDriverQualificationEnum(transport.getDriverQualificationEnum().name());

        if (transport.getDriverQualificationEnum().equals(DriverQualificationEnum.BUS_DRIVER)) {
            Bus bus = (Bus) transport;
            returnedTransport.setType(bus.getType());
            returnedTransport.setAmountOfDoors(bus.getAmountOfDoors());
        } else {
            Tram tram = (Tram) transport;
            returnedTransport.setAmountOfRailcar(tram.getAmountOfRailcar());
        }

        Set<Driver> drivers = transport.getDrivers();
        if (! drivers.isEmpty()) {
            Set<Long> trId = drivers.stream().map(Driver::getId).collect(Collectors.toSet());
            returnedTransport.setDriversId(new HashSet<>(trId));
        }

        Set<Route> routes = transport.getRoute();
        if (! routes.isEmpty()) {
            Set<Long> routesId = routes.stream().map(Route::getId).collect(Collectors.toSet());
            returnedTransport.setDriversId(new HashSet<>(routesId));
        }

        return returnedTransport;
    }
}
