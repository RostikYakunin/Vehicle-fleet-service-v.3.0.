package com.vehicle.api.controllers;

import com.vehicle.api.dto.RouteDto;
import com.vehicle.api.dto.returned_value.Converter;
import com.vehicle.api.dto.returned_value.ReturnedRoute;
import com.vehicle.api.models.routes.Route;
import com.vehicle.api.servises.RouteServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping ("/api/routes")
public class RouteController {

    private final RouteServiceI routeService;

    @Autowired
    public RouteController(RouteServiceI routeService) {
        this.routeService = routeService;
    }

    @PostMapping
    public ResponseEntity <Route> createRoute (@RequestBody @Valid RouteDto routeDto) {
        Route route = routeService.addRoute(routeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(route);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<ReturnedRoute> findRouteById (@PathVariable long id) {
        Route route = routeService.findRouteById(id).orElseThrow(() -> new RuntimeException("Route with id=" + id + " not found"));

        return ResponseEntity.ok(Converter.convertToReturnedRoute(route));
    }

    @PutMapping
    public ResponseEntity <Route> updateRoute (@RequestBody @Valid RouteDto routeDto) {
        return ResponseEntity.ok(routeService.updateRoute(routeDto));
    }

    @DeleteMapping ("/{id}")
    public void deleteRouteById (@PathVariable long id) {
        routeService.deleteRouteById(id);
    }

    @GetMapping
    public ResponseEntity <List<Route>> findAllRoutes () {
        return ResponseEntity.ok(routeService.findAllRoutes());
    }

    // TODO: not work in repo query
    @GetMapping ("/all_without_transport")
    public ResponseEntity <List<Route>> findAllRoutesWithoutTransport () {
        return ResponseEntity.ok(routeService.findAllRoutesWithoutTransport());
    }
}
