package com.vehicle.api.dtos.returned_value.converter;

import com.vehicle.api.dtos.returned_value.ReturnedDriver;
import com.vehicle.api.dtos.returned_value.ReturnedRoute;
import com.vehicle.api.dtos.returned_value.ReturnedTransport;
import com.vehicle.api.models.drivers.Driver;
import com.vehicle.api.models.drivers.DriverQualificationEnum;
import com.vehicle.api.models.routes.Route;
import com.vehicle.api.models.transports.inheritors.Bus;
import com.vehicle.api.models.transports.inheritors.Tram;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ReturnedConverterTest {
    Driver driverTest;
    Bus transportTestBus;
    Route routeTest;

    @BeforeEach
    void setUp () {
        // test transport
        transportTestBus = new Bus("testBrand", 30, DriverQualificationEnum.BUS_DRIVER,
                "testType", 3);
        transportTestBus.setId(1L);
        transportTestBus.getRoute().add(new Route(1L, "testStartWay", "testEndWay"));
        transportTestBus.getDrivers().add(new Driver(1L, "testName", "testSurname",
                "testPhoneNumber", DriverQualificationEnum.BUS_DRIVER));

        // test driver
        driverTest = new Driver("testName", "testSurname", "testPhoneNumber",
                DriverQualificationEnum.BUS_DRIVER
        );
        driverTest.setId(1L);
        driverTest.getTransport().add(new Bus(1L,"testBrand", 30, DriverQualificationEnum.BUS_DRIVER,
                "testType", 3));
        driverTest.getRoute().add(new Route(1L, "testStartWay", "testEndWay"));

        // test route
        routeTest = new Route("testStart", "testEnd");
        routeTest.setId(1L);
        routeTest.getTransports().add(new Bus(1L,"testBrand", 30, DriverQualificationEnum.BUS_DRIVER,
                "testType", 3));
        routeTest.getDrivers().add(new Driver(1L, "testName", "testSurname", "testPhoneNumber",
                DriverQualificationEnum.BUS_DRIVER));

        System.out.println(transportTestBus);
        System.out.println(driverTest);
        System.out.println(routeTest);
    }

    @Test
    void afterRouteInput_thenReturnsNewReturnedRoute() {
        //given
        ReturnedRoute expectedRoute = new ReturnedRoute(
                "testStart",
                "testEnd");
        expectedRoute.setId(1L);
        expectedRoute.getDriversId().add(1L);
        expectedRoute.getTransportsId().add(1L);

        //when
        ReturnedRoute actualRoute = ReturnedConverter.convertToReturnedRoute(routeTest);

        //then
        assertEquals(expectedRoute, actualRoute);
        assertEquals(expectedRoute.getTransportsId(), actualRoute.getTransportsId());
        assertEquals(expectedRoute.getDriversId(), actualRoute.getDriversId());
    }

    @Test
    void afterDriverInput_thenReturnNewReturnedDriver() {
        //given
        ReturnedDriver expectedDriver = new ReturnedDriver(
                "testName", "testSurname", "testPhoneNumber",
                DriverQualificationEnum.BUS_DRIVER.name());
        expectedDriver.setId(1L);
        expectedDriver.getRouteId().add(1L);
        expectedDriver.getTransportId().add(1L);

        //when
        ReturnedDriver actualDriver = ReturnedConverter.convertToReturnedDriver(driverTest);

        //then
        assertEquals(expectedDriver, actualDriver);
        assertEquals(expectedDriver.getRouteId(), expectedDriver.getRouteId());
    }

    @Test
    void afterTransportInput_thenReturnNewReturnedTransport() {
        //given
        Tram transportTestTram = new Tram("testTram", 40,
                DriverQualificationEnum.TRAM_DRIVER, 4);
        transportTestTram.setId(1L);
        transportTestTram.getDrivers().add(new Driver(1L, "testName", "testSurname",
                "testPhoneNumber", DriverQualificationEnum.BUS_DRIVER));
        transportTestTram.getRoute().add(new Route(1L, "testStartWay", "testEndWay"));

        ReturnedTransport expectedBus = new ReturnedTransport("testBrand", 30,
                DriverQualificationEnum.BUS_DRIVER.name(), "testType", 3);
        expectedBus.setId(1L);
        expectedBus.getDriversId().add(1L);
        expectedBus.getRoutesId().add(1L);

        ReturnedTransport expectedTram = new ReturnedTransport("testTram", 40,
                DriverQualificationEnum.TRAM_DRIVER.name(), 4);
        expectedTram.setId(1L);
        expectedTram.getRoutesId().add(1L);
        expectedTram.getDriversId().add(1L);

        //when
        ReturnedTransport actualBus = ReturnedConverter.convertToReturnedTransport(transportTestBus);
        ReturnedTransport actualTram = ReturnedConverter.convertToReturnedTransport(transportTestTram);

        //then
        assertEquals(expectedBus, actualBus);
        assertEquals(expectedTram, actualTram);
    }
}