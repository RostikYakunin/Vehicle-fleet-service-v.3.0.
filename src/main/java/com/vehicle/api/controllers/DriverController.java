package com.vehicle.api.controllers;

import com.vehicle.api.dto.DriverDto;
import com.vehicle.api.models.drivers.Driver;
import com.vehicle.api.servises.DriverServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/drivers")
@Validated
public class DriverController {

    private final DriverServiceI driverService;

    public DriverController(DriverServiceI driverService) {
        this.driverService = driverService;
    }

    @GetMapping
    public ResponseEntity<List<Driver>> findAllDrivers() {
        return ResponseEntity.ok(driverService.findAllDrivers());
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


}
