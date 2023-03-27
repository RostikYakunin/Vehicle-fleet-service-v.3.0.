package com.vehicle.api.controllers;

import com.vehicle.api.dto.TransportDto;
import com.vehicle.api.dto.returned_value.Converter;
import com.vehicle.api.dto.returned_value.ReturnedTransport;
import com.vehicle.api.models.transports.Transport;
import com.vehicle.api.servises.TransportServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public ResponseEntity <ReturnedTransport> findTransportById (@PathVariable long id) {
        Transport transport = transportService.findTransportById(id)
                .orElseThrow(() -> new RuntimeException("Transport with " + id + " not found"));

        return ResponseEntity.ok(Converter.convertToReturnedTransport(transport));
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
    public ResponseEntity <List<ReturnedTransport>> findAllTransports () {
        Set<ReturnedTransport> transports = transportService.findAllTransports().stream().map(Converter::convertToReturnedTransport).collect(Collectors.toSet());
        return ResponseEntity.ok(new ArrayList<>(transports));
    }

    @GetMapping ("/by_brand/{brand}")
    public ResponseEntity <List<Transport>> findTransportByBrand (@PathVariable String brand) {
        return ResponseEntity.ok(transportService.findTransportByBrand(brand));
    }

    @GetMapping ("/without_driver")
    public ResponseEntity <List<Transport>> findTransportWithoutDriver () {
        return ResponseEntity.ok(transportService.findTransportWithoutDriver());
    }

    // TODO: test not work
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
