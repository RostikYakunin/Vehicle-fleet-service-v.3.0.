package com.vehicle.api.repos;

import com.vehicle.api.models.drivers.DriverQualificationEnum;
import com.vehicle.api.models.transports.Transport;
import com.vehicle.api.models.transports.inheritors.Bus;
import com.vehicle.api.models.transports.inheritors.Tram;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TransportRepoITest {

    @Autowired
    TransportRepoI transportRepo;

    Bus testBus;

    @BeforeEach
    void setUp() {
        testBus = new Bus("testBus", 30, DriverQualificationEnum.BUS_DRIVER, "testType", 3);

        transportRepo.save(testBus);
    }

    @AfterEach
    void tearDown() {
        transportRepo.deleteAll();
    }

    @Test
    void findTransportByBrand_inputBrandOfBusAndReturnObjBus() {
        //given
        Bus expectedBus = new Bus();
        expectedBus.setBrandOfTransport("testBus");

        //when
        Transport actualBus = transportRepo.findTransportByBrand("testBus").get(0);

        //then
        assertEquals(expectedBus.getBrandOfTransport(), actualBus.getBrandOfTransport());
    }

    @Test
    void findTransportWithoutDriver_inputTransportAndReturnObjWithoutDriver() {
        //given
        Bus expectedBus = testBus;

        //when
        Optional<Transport> actualTransport = transportRepo.findTransportWithoutDriver().stream().findFirst();

        //then
        assertEquals(expectedBus, actualTransport.get());
    }
}