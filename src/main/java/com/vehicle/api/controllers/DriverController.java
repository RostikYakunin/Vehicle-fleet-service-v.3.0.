package com.vehicle.api.controllers;

import com.vehicle.api.dto.DriverDto;
import com.vehicle.api.models.drivers.Driver;
import com.vehicle.api.models.transports.Transport;
import com.vehicle.api.servises.DriverServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/drivers")
@Validated
public class DriverController {

    private final DriverServiceI driverService;

    public DriverController(DriverServiceI driverService) {
        this.driverService = driverService;
    }

    @PostMapping
    public ResponseEntity<Driver> createDriver(@RequestBody @Valid DriverDto driverDto) {
        Driver driver = driverService.addDriver(driverDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(driver);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Driver> findDriverById(@PathVariable long id) {
        Driver driver = driverService.findDriverById(id).orElseThrow(
                () -> new RuntimeException("Driver with id = " + id + " not found")
        );
        return ResponseEntity.status(HttpStatus.OK).body(driver);
    }

    @PutMapping
    public ResponseEntity<Driver> updateDriver(@RequestBody @Valid DriverDto driverDto) {
        return ResponseEntity.ok(driverService.updateDriver(driverDto));
    }

    @DeleteMapping("/{id}")
    public void deleteDriverById(@PathVariable long id) {
        driverService.deleteDriverById(id);
    }

    //TODO:Test
    @PutMapping("/{d_id}/{t_id}")
    public void addDriverOnTransport(@PathVariable long d_id, @PathVariable long t_id) {
        driverService.addDriverOnTransport(d_id, t_id);
    }

    @GetMapping("/surname/{surname}")
    public ResponseEntity<List<Driver>> findAllDriverBySurname(@PathVariable String surname) {
        return ResponseEntity.status(HttpStatus.OK).body(driverService.findAllDriverBySurname(surname));
    }

    //TODO: test
    @GetMapping("/driver_on_route/{id}")
    public ResponseEntity<Set<Driver>> findAllDriverOnRoute(@PathVariable long id) {
        return ResponseEntity.ok(driverService.findAllDriverOnRoute(id));
    }

    //TODO: test
    @GetMapping("/route_without_driver")
    public ResponseEntity<List<Transport>> findAllTransportsWithoutDriver(){
        return ResponseEntity.ok(driverService.findAllTransportsWithoutDriver());
    }

    @GetMapping
    public ResponseEntity<List<Driver>> findAllDrivers() {
        return ResponseEntity.ok(driverService.findAllDrivers());
    }

}
