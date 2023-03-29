package com.vehicle.api.services.interfaces;

import com.vehicle.api.mediators.dto.RouteDto;
import com.vehicle.api.models.routes.Route;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RouteServiceI {

    Route addRoute(RouteDto routeDto);

    Optional<Route> findRouteById(Long id);

    Route updateRoute(RouteDto routeDto);

    boolean deleteRouteById(Long id);

    List<Route> findAllRoutes();

    List<Route> findAllRoutesWithoutTransport();

}
