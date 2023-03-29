package com.vehicle.api.controllers;

import com.vehicle.api.mediators.dto.RouteDto;
import com.vehicle.api.mediators.returned_value.converter.ReturnedConverter;
import com.vehicle.api.mediators.returned_value.ReturnedRoute;
import com.vehicle.api.models.routes.Route;
import com.vehicle.api.services.interfaces.RouteServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/routes")
public class RouteController {

    private final RouteServiceI routeService;

    @Autowired
    public RouteController(RouteServiceI routeService) {
        this.routeService = routeService;
    }

    @PostMapping
    public ResponseEntity<ReturnedRoute> createRoute(@RequestBody @Valid RouteDto routeDto) {
        ReturnedRoute route = ReturnedConverter.convertToReturnedRoute(routeService.addRoute(routeDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(route);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReturnedRoute> findRouteById(@PathVariable long id) {
        Route route = routeService.findRouteById(id)
                .orElseThrow(() -> new RuntimeException("Route with id=" + id + " not found"));

        return ResponseEntity.ok(ReturnedConverter.convertToReturnedRoute(route));
    }

    @PutMapping
    public ResponseEntity<ReturnedRoute> updateRoute(@RequestBody @Valid RouteDto routeDto) {
        ReturnedRoute route = ReturnedConverter.convertToReturnedRoute(routeService.updateRoute(routeDto));
        return ResponseEntity.ok(route);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRouteById(@PathVariable long id) {
        routeService.deleteRouteById(id);
        return ResponseEntity.ok("Route with id " + id + " was deleted");
    }

    @GetMapping
    public ResponseEntity<List<ReturnedRoute>> findAllRoutes() {
        List<ReturnedRoute> routes = routeService.findAllRoutes().stream()
                .map(ReturnedConverter::convertToReturnedRoute)
                .collect(Collectors.toList());
        return ResponseEntity.ok(routes);
    }

    @GetMapping("/all_without_transport")
    public ResponseEntity<List<ReturnedRoute>> findAllRoutesWithoutTransport() {
        List<ReturnedRoute> routes = routeService.findAllRoutesWithoutTransport().stream()
                .map(ReturnedConverter::convertToReturnedRoute)
                .collect(Collectors.toList());
        return ResponseEntity.ok(routes);
    }
}
