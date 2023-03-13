package com.vehicle.api.servises;

import com.vehicle.api.models.routes.Route;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RouteServiceI {

    Route addRoute(Route route);

    Optional<Route> findRouteById(Long id);

    Route updateRoute(Route route);

    boolean deleteRouteById(Long id);

    List<Route> findAllRoutes();

    List<Route> findAllRoutesWithoutTransport();

}
