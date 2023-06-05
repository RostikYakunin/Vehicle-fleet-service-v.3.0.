package com.vehicle.api.dtos.dto.handler;

import com.vehicle.api.dtos.dto.TransportDto;
import com.vehicle.api.models.drivers.Driver;
import com.vehicle.api.models.drivers.DriverQualificationEnum;
import com.vehicle.api.models.routes.Route;
import com.vehicle.api.models.transports.Transport;
import com.vehicle.api.models.transports.inheritors.Bus;
import com.vehicle.api.models.transports.inheritors.Tram;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TransportDtoHandlerTest {
    Bus testBus;
    Tram testTram;

    TransportDto testBusDto;

    TransportDto testTramDto;

    @BeforeEach
    void setUp() {
        testBus = new Bus(1L, "testBus", 30, DriverQualificationEnum.BUS_DRIVER, "testType", 3);
        testBus.getDrivers().add(new Driver(1L));
        testBus.getRoute().add(new Route(1L));

        testTram = new Tram(1L, "testTram", 40, DriverQualificationEnum.TRAM_DRIVER, 4);
        testTram.getDrivers().add(new Driver(1L));
        testTram.getRoute().add(new Route(1L));

        testBusDto = new TransportDto(1L, "testBus", 30, "BUS", "testType", 3);
        testBusDto.getDrivers().add(new Driver(1L));
        testBusDto.getRoutes().add(new Route(1L));

        testTramDto = new TransportDto(1L, "testTram", 40, "TRAM", 4);
        testTramDto.getDrivers().add(new Driver(1L));
        testTramDto.getRoutes().add(new Route(1L));
    }

    @Test
    void createTransportDto_inputBusAndReturnTransportBusDto() {
        //given
        testBusDto.getDrivers().clear();
        testBusDto.getRoutes().clear();

        //when
        TransportDto actualBus = TransportDtoHandler.createTransportDto(testBus);

        //then
        assertEquals(testBusDto, actualBus);
        assertNull(actualBus.getAmountOfRailcar());
    }

    @Test
    void createTransportDto_inputTramAndReturnTransportTramDto() {
        //given
        testTramDto.getDrivers().clear();
        testTramDto.getRoutes().clear();

        //when
        TransportDto actualTram = TransportDtoHandler.createTransportDto(testTram);

        //then
        assertEquals(testTramDto, actualTram);
        assertNull(actualTram.getAmountOfDoors());
        assertNull(actualTram.getType());
    }

    @Test
    void mappingDtoToTransportMethodAdd_inputBusDtoReturnsBus() {
        //given

        //when
        Transport actualBus = TransportDtoHandler.mappingDtoToTransportMethodAdd(testBusDto);

        //then
        assertEquals(testBus, actualBus);
    }

    @Test
    void mappingDtoToTransportMethodAdd_inputTramDtoReturnsTram() {
        //given

        //when
        Transport actualTram = TransportDtoHandler.mappingDtoToTransportMethodAdd(testTramDto);

        //then
        assertEquals(testTram, actualTram);
    }

    @Test
    void mappingDtoToTransportMethodAdd_inputTransportDtoAndExpectedException() {
        //given
        TransportDto transportDto = new TransportDto();
        transportDto.setId(1L);

        //when
        RuntimeException runtimeException = assertThrows(RuntimeException.class,
                () -> TransportDtoHandler.mappingDtoToTransportMethodAdd(transportDto),
                "Expected runtime exception");

        //then
        assertEquals("Transport: " + transportDto + " cannot mapped to transport", runtimeException.getMessage());
    }
    @Test
    void mappingDtoToTransportMethodUpdate_inputBusDtoAndReturnBus() {
        //given
        Bus expectedBus = new Bus(1L, "testBus", 30, DriverQualificationEnum.BUS_DRIVER, "testType", 3);
        expectedBus.getDrivers().add(new Driver(1L));
        expectedBus.getRoute().add(new Route(1L));

        //when
        Transport actualBus = TransportDtoHandler.mappingDtoToTransportMethodUpdate(testBusDto, Optional.of(testBus));

        //then
        assertEquals(expectedBus, actualBus);
    }

    @Test
    void mappingDtoToTransportMethodUpdate_inputTramDtoAndReturnTram() {
        //given
        Tram expectedTram = new Tram(1L, "testTram", 40, DriverQualificationEnum.TRAM_DRIVER, 4);
        expectedTram.getDrivers().add(new Driver(1L));
        expectedTram.getRoute().add(new Route(1L));

        //when
        Transport actualTram = TransportDtoHandler.mappingDtoToTransportMethodUpdate(testTramDto, Optional.of(testTram));

        //then
        assertEquals(expectedTram, actualTram);
    }

    @Test
    void mappingDtoToTransportMethodUpdate_inputNullAndExpectRuntimeException () {
        //given

        //when
        RuntimeException runtimeException = new RuntimeException(
                "My runtime exception",
                assertThrows(
                        RuntimeException.class,
                        () -> TransportDtoHandler.mappingDtoToTransportMethodUpdate(testTramDto, Optional.of(null)),
                        "runtime"
                ));

        //then
        assertTrue(runtimeException.getMessage().contains("runtime"));
    }
}