package com.vehicle.api.dto.handler;

import com.vehicle.api.dto.DriverDto;
import com.vehicle.api.dto.RouteDto;
import com.vehicle.api.models.drivers.Driver;
import com.vehicle.api.models.drivers.DriverQualificationEnum;
import com.vehicle.api.models.routes.Route;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class RouteDtoHandler {
    public static Route mappingDtoToRouteMethodAdd(RouteDto routeDto) {
        Route route = new Route();

        route.setStartOfWay(routeDto.getStartOfWay());
        route.setEndOfWay(routeDto.getEndOfWay());

        log.info("New route was mapped: " + route);
        return route;
    }

    public static Route mappingDtoToRouteMethodUpdate(RouteDto routeDto, Optional<Route> route) {
        if (route.isEmpty()) {
            log.error("Error, route with id = " + routeDto.getId() + " not found");
            throw new RuntimeException("Error, route with id = " + routeDto.getId() + " not found");
        }

        if (routeDto.getStartOfWay() != null) {
            log.info("Route`s start way was updated from " + route.get().getStartOfWay() + " to " + routeDto.getStartOfWay());
            route.get().setStartOfWay(routeDto.getStartOfWay());
        }

        if (routeDto.getEndOfWay() != null) {
            log.info("Route`s end way was updated from " + route.get().getEndOfWay() + " to " + routeDto.getEndOfWay());
            route.get().setEndOfWay(routeDto.getEndOfWay());
        }


        return route.get();
    }
}
