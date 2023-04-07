package com.vehicle.api.repos;

import com.vehicle.api.models.drivers.Driver;
import com.vehicle.api.models.drivers.DriverQualificationEnum;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DriverRepoITest {
    @Autowired
    DriverRepoI driverRepo;

    @BeforeEach
    void setUp() {
        driverRepo.save(new Driver("testName", "testSurname", "1223334444", DriverQualificationEnum.TRAM_DRIVER));
    }

    @AfterEach
    void turnDown() {
        driverRepo.deleteAll();
    }

    @Test
    void findDriversBySurname_inputSurnameAndReturnObjWithSurname() {
        //given
        Driver expectedDriver = new Driver();
        expectedDriver.setSurnameOfDriver("testSurname");

        //when
        Driver actualDriver = driverRepo.findDriversBySurname("testSurname").get(0);

        //then
        assertEquals(expectedDriver.getSurnameOfDriver(), actualDriver.getSurnameOfDriver());
    }
}