package com.vehicle.api.mediators.dto.handler;

import com.vehicle.api.mediators.dto.DriverDto;
import com.vehicle.api.models.drivers.Driver;
import com.vehicle.api.models.drivers.DriverQualificationEnum;
import com.vehicle.api.models.routes.Route;
import com.vehicle.api.models.transports.inheritors.Bus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DriverDtoHandlerTest {
    DriverDto testDriverDto;
    Driver testDriver;

    @BeforeEach
    void setUp() {
        // test driver dto
        testDriverDto = new DriverDto(1L, "testName", "testSurname",
                "testPhoneNumber", "bus");
        testDriverDto.getTransport().add(new Bus(1L, "testBrand", 30,
                DriverQualificationEnum.BUS_DRIVER, "testType", 3));
        testDriverDto.getRoute().add(new Route(1L, "testStart", "testEnd"));

        // test driver
        testDriver = new Driver(1L, "testName", "testSurname",
                "testPhoneNumber", DriverQualificationEnum.BUS_DRIVER);
        testDriver.getTransport().add(new Bus(1L, "testBrand", 30,
                DriverQualificationEnum.BUS_DRIVER, "testType", 3));
        testDriver.getRoute().add(new Route(1L, "testStart", "testEnd"));
    }

    @Test
    void mappingDtoToDriverMethodAdd() {
        //given
        Driver expectedDriver = new Driver("testName", "testSurname",
                "testPhoneNumber", DriverQualificationEnum.BUS_DRIVER);
        expectedDriver.getTransport().add(new Bus(1L, "testBrand", 30,
                DriverQualificationEnum.BUS_DRIVER, "testType", 3));
        expectedDriver.getRoute().add(new Route(1L, "testStart", "testEnd"));

        //when
        Driver actualDriver = DriverDtoHandler.mappingDtoToDriverMethodAdd(testDriverDto);

        //then
        assertEquals(expectedDriver.getId(), actualDriver.getId());
        assertEquals(expectedDriver.getNameOfDriver(), actualDriver.getNameOfDriver());
        assertEquals(expectedDriver.getSurnameOfDriver(), actualDriver.getSurnameOfDriver());
        assertEquals(expectedDriver.getPhoneNumber(), actualDriver.getPhoneNumber());
    }

    @Test
    void mappingDtoToDriverMethodUpdate() {
        //given
        Driver expectedDriver = new Driver(1L, "testName", "testSurname",
                "testPhoneNumber", DriverQualificationEnum.BUS_DRIVER);
        expectedDriver.getTransport().add(new Bus(1L, "testBrand", 30,
                DriverQualificationEnum.BUS_DRIVER, "testType", 3));
        expectedDriver.getRoute().add(new Route(1L, "testStart", "testEnd"));

        //when
        Driver actualDriver = DriverDtoHandler.mappingDtoToDriverMethodUpdate(testDriverDto, Optional.of(testDriver));

        //then
        assertEquals(expectedDriver, actualDriver);
    }
}