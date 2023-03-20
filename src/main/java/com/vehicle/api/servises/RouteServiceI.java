package com.vehicle.api.servises;

import com.vehicle.api.dto.RouteDto;
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
