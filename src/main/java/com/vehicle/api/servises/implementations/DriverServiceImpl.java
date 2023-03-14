package com.vehicle.api.servises.implementations;

import com.vehicle.api.dto.DriverDto;
import com.vehicle.api.dto.handler.DriverDtoHandler;
import com.vehicle.api.models.drivers.Driver;
import com.vehicle.api.models.routes.Route;
import com.vehicle.api.models.transports.Transport;
import com.vehicle.api.repos.DriverRepoI;
import com.vehicle.api.servises.DriverServiceI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class DriverServiceImpl implements DriverServiceI {

    private final DriverRepoI driverRepo;

    public DriverServiceImpl(DriverRepoI driverRepo) {
        this.driverRepo = driverRepo;
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
        log.info("Driver update successful");

        return driverRepo.save(upgradeDriver);
    }

    @Override
    public boolean deleteDriverById(Long id) {
        if (driverRepo.findById(id).isEmpty()) {
            log.warn("Error, driver with id = " + id + " not found");
            return false;
        }

        if (driverRepo.findById(id).get().getTransport() != null) {
            log.warn("This driver can`t be deleted, driver is assigned to the transport = " + driverRepo.findById(id).get().getTransport().toString());
            return false;
        }

        log.info("Driver : " + driverRepo.findById(id) + " was deleted");
        driverRepo.deleteById(id);
        return true;
    }

    @Override
    public boolean addDriverOnTransport(Driver driver, Transport transport) {
        return false;
    }

    @Override
    public List<Driver> findAllDriverBySurname(String surname) {
        return null;
    }

    @Override
    public Set<Driver> findAllDriverOnRoute(Route route) {
        return route.getDrivers();
    }

    @Override
    public List<Transport> findAllTransportsWithoutDriver() {
        return null;
    }

    @Override
    public List<Driver> findAllDrivers() {
        return (List<Driver>) driverRepo.findAll();
    }
}
