package com.vehicle.api.servises.implementations;

import com.sun.jdi.connect.spi.TransportService;
import com.vehicle.api.dto.DriverDto;
import com.vehicle.api.dto.TransportDto;
import com.vehicle.api.dto.handler.DriverDtoHandler;
import com.vehicle.api.dto.handler.TransportDtoHandler;
import com.vehicle.api.models.drivers.Driver;
import com.vehicle.api.models.transports.Transport;
import com.vehicle.api.repos.DriverRepoI;
import com.vehicle.api.repos.RouteRepoI;
import com.vehicle.api.repos.TransportRepoI;
import com.vehicle.api.servises.DriverServiceI;
import com.vehicle.api.servises.TransportServiceI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Slf4j
@Transactional
public class DriverServiceImpl implements DriverServiceI {

    private final DriverRepoI driverRepo;
    private final TransportRepoI transportRepo;
    private final RouteRepoI routeRepo;
    private final TransportServiceI transportService;

    @Autowired
    public DriverServiceImpl(DriverRepoI driverRepo, TransportRepoI transportRepo, RouteRepoI routeRepo, TransportServiceI transportService) {
        this.driverRepo = driverRepo;
        this.transportRepo = transportRepo;
        this.routeRepo = routeRepo;
        this.transportService = transportService;
    }


    @Override
    public Driver addDriver(DriverDto driverDto) {
        Driver driver = DriverDtoHandler.mappingDtoToDriverMethodAdd(driverDto);
        log.info("Driver was added to db: " + driver);

        return driverRepo.save(driver);
    }

    @Override
    public Optional<Driver> findDriverById(Long id) {
        if (driverRepo.findById(id).isEmpty()) {
            log.warn("Error, driver with id = " + id + " not found");
            return Optional.empty();
        }

        return driverRepo.findById(id);
    }

    @Override
    public Driver updateDriver(DriverDto driverDto) {
        Optional<Driver> driver = driverRepo.findById(driverDto.getId());
        Driver upgradeDriver = DriverDtoHandler.mappingDtoToDriverMethodUpdate(driverDto, driver);
        log.info("Driver successfully updated " + upgradeDriver);

        return driverRepo.save(upgradeDriver);
    }

    @Override
    public boolean deleteDriverById(Long id) {
        if (driverRepo.findById(id).isEmpty()) {
            log.warn("Error, driver with id = " + id + " not found");
            return false;
        }

        if (!driverRepo.findById(id).get().getTransport().isEmpty()) {
            log.warn("This driver can`t be deleted, driver is assigned to the transport = " + driverRepo.findById(id).get().getTransport().toString());
            return false;
        }

        log.info("Driver : " + driverRepo.findById(id) + " was deleted");
        driverRepo.deleteById(id);
        return true;
    }

    @Override
    public boolean addDriverOnTransport(long driverId, long transportId) {
        Optional<Driver> driver = driverRepo.findById(driverId);
        Optional<Transport> transport = transportRepo.findById(transportId);

        if (driver.isEmpty() || transport.isEmpty()) {
            log.warn("Driver or transport is null !");
            return false;
        }

//        if (!transport.get().getDrivers().isEmpty()) {
//            log.warn("This transport " + transport.get() + " already has driver " + transport.get().getDrivers());
//            return false;
//        }

        transport.get().setDrivers(Collections.singleton(driver.get()));
        TransportDto transportDto = TransportDtoHandler.createTransportDto(transport.get());

        transportService.updateTransport(transportDto);
        log.info("Driver: " + driver.get() + " successful added to transport " + transport.get());
        return true;
    }

    @Override
    public List<Driver> findAllDriverBySurname(String surname) {
        List<Driver> driverList;
        driverList = driverRepo.findDriversBySurname(surname);

        log.info("Users list was successful prepared");
        return driverList;
    }

    @Override
    public Set<Driver> findAllDriverOnRoute(long routeId) {
        if (routeRepo.findById(routeId).isEmpty()) {
            log.warn("Route with id= " + routeId + " not found");
            return Collections.emptySet();
        }
        return routeRepo.findById(routeId).get().getDrivers();
    }

    @Override
    public List<Transport> findAllTransportsWithoutDriver() {
        return transportRepo.findTransportWithoutDriver();
    }

    @Override
    public List<Driver> findAllDrivers() {
        return (List<Driver>) driverRepo.findAll();
    }
}
