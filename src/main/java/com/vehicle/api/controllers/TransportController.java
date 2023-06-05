package com.vehicle.api.controllers;

import com.vehicle.api.dtos.dto.TransportDto;
import com.vehicle.api.dtos.returned_value.ReturnedTransport;
import com.vehicle.api.dtos.returned_value.converter.ReturnedConverter;
import com.vehicle.api.models.transports.Transport;
import com.vehicle.api.services.interfaces.TransportServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transports")
public class TransportController {

    TransportServiceI transportService;

    @Autowired
    public TransportController(TransportServiceI transportService) {
        this.transportService = transportService;
    }

    @PostMapping
    public ResponseEntity<ReturnedTransport> createTransport(@RequestBody TransportDto transportDto) {
        ReturnedTransport transport = ReturnedConverter.convertToReturnedTransport(transportService.addTransport(transportDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(transport);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReturnedTransport> findTransportById(@PathVariable long id) {
        Transport transport = transportService.findTransportById(id)
                .orElseThrow(() -> new RuntimeException("Transport with " + id + " not found"));

        return ResponseEntity.ok(ReturnedConverter.convertToReturnedTransport(transport));
    }

    @PutMapping
    public ResponseEntity<ReturnedTransport> updateTransport(@RequestBody TransportDto transportDto) {
        ReturnedTransport transport = ReturnedConverter.convertToReturnedTransport(transportService.updateTransport(transportDto));
        return ResponseEntity.ok(transport);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTransportById(@PathVariable long id) {
        transportService.deleteTransportById(id);
        return ResponseEntity.ok("Transport with id " + id + " was deleted");
    }

    @GetMapping
    public ResponseEntity<List<ReturnedTransport>> findAllTransports() {
        List<ReturnedTransport> transports = transportService.findAllTransports().stream()
                .map(ReturnedConverter::convertToReturnedTransport)
                .collect(Collectors.toList());
        return ResponseEntity.ok(transports);
    }

    @GetMapping("/by_brand/{brand}")
    public ResponseEntity<List<ReturnedTransport>> findTransportByBrand(@PathVariable String brand) {
        List<ReturnedTransport> transports = transportService.findTransportByBrand(brand).stream()
                .map(ReturnedConverter::convertToReturnedTransport)
                .collect(Collectors.toList());
        return ResponseEntity.ok(transports);
    }

    @GetMapping("/without_driver")
    public ResponseEntity<List<ReturnedTransport>> findTransportWithoutDriver() {
        List<ReturnedTransport> transports = transportService.findTransportWithoutDriver().stream()
                .map(ReturnedConverter::convertToReturnedTransport)
                .collect(Collectors.toList());
        return ResponseEntity.ok(transports);
    }

    @PutMapping("/transport_to_route/{transportId}/{routeId}")
    public ResponseEntity<String> addTransportToRoute(@PathVariable long transportId, @PathVariable long routeId) {
        transportService.addTransportToRoute(transportId, routeId);
        return ResponseEntity.ok("Transport with id " + transportId + " was added to route with id " + routeId);
    }

    @DeleteMapping("/transport_from_route/{transportId}/{routeId}")
    public ResponseEntity<String> removeTransportFromRoute(@PathVariable long transportId, @PathVariable long routeId) {
        transportService.removeTransportFromRoute(transportId, routeId);
        return ResponseEntity.ok("Transport with id " + transportId + " was deleted from route with id " + routeId);
    }
}
