package com.vehicle.api.mediators.returned_value.converter;

import com.vehicle.api.mediators.returned_value.ReturnedDriver;
import com.vehicle.api.mediators.returned_value.ReturnedRoute;
import com.vehicle.api.mediators.returned_value.ReturnedTransport;
import com.vehicle.api.models.drivers.Driver;
import com.vehicle.api.models.drivers.DriverQualificationEnum;
import com.vehicle.api.models.routes.Route;
import com.vehicle.api.models.transports.Transport;
import com.vehicle.api.models.transports.inheritors.Bus;
import com.vehicle.api.models.transports.inheritors.Tram;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ReturnedConverter {

    public static ReturnedRoute convertToReturnedRoute(Route route) {
        ReturnedRoute returnedRoute = new ReturnedRoute();

        returnedRoute.setId(route.getId());
        returnedRoute.setStartOfWay(route.getStartOfWay());
        returnedRoute.setEndOfWay(route.getEndOfWay());

        Set<Transport> transports = route.getTransports();
        if (!transports.isEmpty()) {
            returnedRoute.getTransportsId().addAll(
                    transports.stream()
                            .map(Transport::getId)
                            .collect(Collectors.toSet())

            );
        }

        Set<Driver> drivers = route.getDrivers();
        if (!drivers.isEmpty()) {
            returnedRoute.getDriversId().addAll(
                    drivers.stream()
                            .map(Driver::getId)
                            .collect(Collectors.toSet())

            );
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

        Set<Route> route = driver.getRoute();
        if (!route.isEmpty()) {
            Set<Long> routesId = route.stream()
                    .map(Route::getId)
                    .collect(Collectors.toSet());

            returnedDriver.getRouteId().addAll(routesId);
        }

        Set<Transport> transports = driver.getTransport();
        if (!transports.isEmpty()) {
            Set<Long> transportId = transports.stream()
                    .map(Transport::getId)
                    .collect(Collectors.toSet());

            returnedDriver.getTransportId().addAll(transportId);
        }

        return returnedDriver;
    }

    public static ReturnedTransport convertToReturnedTransport(Transport transport) {
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
        if (!drivers.isEmpty()) {
            Set<Long> trId = drivers.stream()
                    .map(Driver::getId)
                    .collect(Collectors.toSet());

            returnedTransport.getDriversId().addAll(trId);
        }

        Set<Route> routes = transport.getRoute();
        if (!routes.isEmpty()) {
            Set<Long> routesId = routes.stream()
                    .map(Route::getId)
                    .collect(Collectors.toSet());

            returnedTransport.getRoutesId().addAll(routesId);
        }

        return returnedTransport;
    }
}
