package com.vehicle.api.controllers;

import com.vehicle.api.dto.TransportDto;
import com.vehicle.api.models.transports.Transport;
import com.vehicle.api.servises.TransportServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/transports")
public class TransportController {

    TransportServiceI transportService;

    @Autowired
    public TransportController(TransportServiceI transportService) {
        this.transportService = transportService;
    }

    @PostMapping
    public ResponseEntity <Transport> createTransport (@RequestBody TransportDto transportDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transportService.addTransport(transportDto));
    }

    @GetMapping ("/{id}")
    public ResponseEntity <Transport> findTransportById (@PathVariable long id) {
        return ResponseEntity.ok(transportService.findTransportById(id)
                .orElseThrow( () -> new RuntimeException("Transport with " + id + " not found"))
        );
    }

    @PutMapping
    public ResponseEntity <Transport> updateTransport (@RequestBody TransportDto transportDto) {
        return ResponseEntity.ok(transportService.updateTransport(transportDto));
    }

    @DeleteMapping ("/{id}")
    public void deleteTransportById (@PathVariable long id) {
        transportService.deleteTransport(id);
    }

    @GetMapping
    public ResponseEntity <List<Transport>> findAllTransports () {
        return ResponseEntity.ok(transportService.findAllTransports());
    }

    @GetMapping ("/by_brand/{brand}")
    public ResponseEntity <List<Transport>> findTransportByBrand (@PathVariable String brand) {
        return ResponseEntity.ok(transportService.findTransportByBrand(brand));
    }

    // TODO: check method with null argument
    @GetMapping ("/without_driver")
    public ResponseEntity <List<Transport>> findTransportWithoutDriver () {
        return ResponseEntity.ok(transportService.findTransportWithoutDriver());
    }

    // TODO: see service
    @PutMapping ("/transport_to_route/{transportId}/{routeId}")
    public void addTransportToRoute (@PathVariable long transportId, @PathVariable long routeId) {
        transportService.addTransportToRoute(transportId, routeId);
    }

    // TODO: test after finishing previous method
    @DeleteMapping ("/transport_from_route/{transportId}/{routeId}")
    public void removeTransportFromRoute(@PathVariable long transportId, @PathVariable long routeId) {
        transportService.removeTransportFromRoute(transportId, routeId);
    }
}
