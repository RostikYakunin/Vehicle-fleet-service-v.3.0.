package com.vehicle.api.dto.handler;

import com.vehicle.api.dto.DriverDto;
import com.vehicle.api.models.drivers.Driver;
import com.vehicle.api.models.drivers.DriverQualificationEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class DriverDtoHandler {

    public static Driver mappingDtoToDriverMethodAdd(DriverDto driverDto) {
        Driver driver = new Driver();
        driver.setNameOfDriver(driverDto.getNameOfDriver());
        driver.setSurnameOfDriver(driverDto.getSurnameOfDriver());
        driver.setPhoneNumber(driverDto.getPhoneNumber());
        driver.setQualificationEnum(
                DriverQualificationEnum.valueOf(driverDto.getQualificationEnum().toUpperCase() + "_DRIVER")
        );
        log.info("New driver was mapped: " + driver);
        return driver;
    }

    public static Driver mappingDtoToDriverMethodUpdate(DriverDto driverDto, Optional<Driver> driver) {
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

        if (driverDto.getPhoneNumber() != null) {
            log.info("Driver`s phone was updated from " + driver.get().getPhoneNumber() + " to " + driverDto.getPhoneNumber());
            driver.get().setPhoneNumber(driverDto.getPhoneNumber());
        }

        if (driverDto.getQualificationEnum() != null) {
            log.info("Driver`s name was updated from " + driver.get().getQualificationEnum() + " to " + driverDto.getQualificationEnum());
            driver.get().setQualificationEnum(DriverQualificationEnum.valueOf(driverDto.getQualificationEnum().toUpperCase() + "_DRIVER"));
        }

        return driver.get();
    }

}
