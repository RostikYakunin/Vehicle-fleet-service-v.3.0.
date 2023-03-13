package com.vehicle.api.servises.implementations;

import com.vehicle.api.dto.DriverDtoUpdate;
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
    public Driver addDriver(Driver driver) {
        Driver save = driverRepo.save(driver);
        log.info("Driver was added : " + save);
        return save;
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
    public Driver updateDriver(DriverDtoUpdate driverDto) {
        Optional<Driver> driver = driverRepo.findById(driverDto.getId());
        return mappingDtoToDriver(driverDto, driver);
    }

    private Driver mappingDtoToDriver(DriverDtoUpdate driverDto, Optional<Driver> driver) {
        if (driver.isEmpty()) {
            log.error("Error, driver with id = " + driverDto.getId() + " not found");
            throw new RuntimeException("Error, driver with id = " + driverDto.getId() + " not found");
        }

        if (driverDto.getNameOfDriver() != null) {
            log.info("Driver`s name was updated from " + driver.get().getNameOfDriver() + " to " + driverDto.getNameOfDriver());
            driver.get().setNameOfDriver(driverDto.getNameOfDriver());
        }

        if (driverDto.getSurnameOfDriver() != null) {
            log.info("Driver`s surname was updated from " + driver.get().getSurnameOfDriver() + " to " + driverDto.getSurnameOfDriver());
            driver.get().setSurnameOfDriver(driverDto.getSurnameOfDriver());
        }

        if (driverDto.getNumberOfPhone() != null) {
            log.info("Driver`s phone was updated from " + driver.get().getPhoneNumber() + " to " + driverDto.getNumberOfPhone());
            driver.get().setPhoneNumber(driverDto.getNumberOfPhone());
        }

        if (driverDto.getQualificationEnum() != null) {
            log.info("Driver`s name was updated from " + driver.get().getQualificationEnum() + " to " + driverDto.getQualificationEnum());
            driver.get().setQualificationEnum(driverDto.getQualificationEnum());
        }

        driverRepo.save(driver.get());
        return driver.get();
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
