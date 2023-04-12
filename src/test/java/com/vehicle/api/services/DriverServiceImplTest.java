package com.vehicle.api.services;

import com.vehicle.api.mediators.dto.DriverDto;
import com.vehicle.api.mediators.dto.TransportDto;
import com.vehicle.api.models.drivers.Driver;
import com.vehicle.api.models.drivers.DriverQualificationEnum;
import com.vehicle.api.models.routes.Route;
import com.vehicle.api.models.transports.Transport;
import com.vehicle.api.models.transports.inheritors.Bus;
import com.vehicle.api.repos.DriverRepoI;
import com.vehicle.api.repos.RouteRepoI;
import com.vehicle.api.repos.TransportRepoI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class DriverServiceImplTest {

    @Autowired
    DriverServiceImpl driverService;
    @MockBean
    DriverRepoI driverRepo;

    @MockBean
    TransportRepoI transportRepo;

    @MockBean
    TransportServiceImpl transportService;

    @MockBean
    RouteRepoI routeRepo;

    @Captor
    ArgumentCaptor<Long> longArgumentCaptor;

    @Captor
    ArgumentCaptor<Driver> driverArgumentCaptor;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    @Captor
    ArgumentCaptor<TransportDto> transportDtoArgumentCaptor;

    DriverDto testDriverDto;

    Driver testDriver;

    Route testRoute;

    @BeforeEach
    void setUp() {
        Bus bus = new Bus();
        bus.setId(1L);

        Route route = new Route();
        route.setId(1L);


        testDriver = new Driver("testName", "testSurname", "testPhone", DriverQualificationEnum.BUS_DRIVER);
        testDriver.setId(1L);
        testDriver.getTransport().add(bus);
        testDriver.getRoute().add(route);

        testDriverDto = new DriverDto(1L, "testName", "testSurname", "testPhone", "BUS");
        testDriverDto.getRoute().add(route);
        testDriverDto.getTransport().add(bus);

        testRoute = new Route(1L, "testStart", "testEnd");
    }

    @Test
    void addDriver_inputDriverDtoReturnDriver() {
        //given

        //when
        when(driverRepo.save(any(Driver.class))).thenReturn(testDriver);

        //then
        Driver actualDriver = driverService.addDriver(testDriverDto);
        assertEquals(testDriver, actualDriver);

        verify(driverRepo, times(1)).save(driverArgumentCaptor.capture());
    }

    @Test
    void findDriverById_inputLongReturnOptionalOfDriver() {
        //given

        //when
        when(driverRepo.findById(anyLong())).thenReturn(Optional.of(testDriver));

        //then
        Optional<Driver> actualDriver = driverService.findDriverById(1L);
        assertEquals(Optional.of(testDriver), actualDriver);

        verify(driverRepo, times(2)).findById(longArgumentCaptor.capture());
    }

    @Test
    void findDriverById_inputLongReturnOptionalEmpty() {
        //given

        //when
        when(driverRepo.findById(anyLong())).thenReturn(Optional.empty());

        //then
        Optional<Driver> actualDriver = driverService.findDriverById(1L);
        assertEquals(Optional.empty(), actualDriver);

        verify(driverRepo, times(1)).findById(longArgumentCaptor.capture());
    }

    @Test
    void updateDriver_inputDriverDtoReturnDriver() {
        //given

        //when
        when(driverRepo.findById(anyLong())).thenReturn(Optional.of(testDriver));
        when(driverRepo.save(any(Driver.class))).thenReturn(testDriver);

        //then
        Driver actualDriver = driverService.updateDriver(testDriverDto);
        assertEquals(testDriver, actualDriver);

        verify(driverRepo, times(1)).findById(longArgumentCaptor.capture());
        verify(driverRepo, times(1)).save(driverArgumentCaptor.capture());
    }

    @Test
    void deleteDriverById_inputLongReturnTrue() {
        //given
        testDriver.getTransport().clear();

        //when
        when(driverRepo.findById(anyLong())).thenReturn(Optional.of(testDriver));

        //then
        boolean actualResult = driverService.deleteDriverById(1L);
        assertTrue(actualResult);

        verify(driverRepo, times(1)).findById(longArgumentCaptor.capture());
        verify(driverRepo, times(1)).deleteById(longArgumentCaptor.capture());
    }

    @Test
    void deleteDriverById_inputEmptyReturnFalse() {
        //given

        //when
        when(driverRepo.findById(anyLong())).thenReturn(Optional.empty());

        //then
        boolean actualResult = driverService.deleteDriverById(1L);
        assertFalse(actualResult);

        verify(driverRepo, times(1)).findById(longArgumentCaptor.capture());
        verify(driverRepo, times(0)).deleteById(longArgumentCaptor.capture());
    }

    @Test
    void deleteDriverById_inputDriverWithNotEmptyDrivers_returnFalse() {
        //given

        //when
        when(driverRepo.findById(anyLong())).thenReturn(Optional.of(testDriver));

        //then
        boolean actualResult = driverService.deleteDriverById(1L);
        assertFalse(actualResult);

        verify(driverRepo, times(1)).findById(longArgumentCaptor.capture());
        verify(driverRepo, times(0)).deleteById(longArgumentCaptor.capture());
    }

    @Test
    void addDriverOnTransport_inputTwoLongAndReturnTrue() {
        //given
        Bus testTransport = new Bus(1L, "testBrand", 30, DriverQualificationEnum.BUS_DRIVER, "testType", 3);

        //when
        when(driverRepo.findById(anyLong())).thenReturn(Optional.of(testDriver));
        when(transportRepo.findById(anyLong())).thenReturn(Optional.of(testTransport));
        when(transportService.updateTransport(any(TransportDto.class))).thenReturn(new Bus());

        //then
        boolean actualResult = driverService.addDriverOnTransport(1L, 1L);
        assertTrue(actualResult);

        verify(driverRepo, times(1)).findById(longArgumentCaptor.capture());
        verify(transportRepo, times(1)).findById(longArgumentCaptor.capture());
        verify(transportService, times(1)).updateTransport(transportDtoArgumentCaptor.capture());
    }

    @Test
    void addDriverOnTransport_inputEmptyDriverAndReturnFalse() {
        //given
        Bus testTransport = new Bus(1L, "testBrand", 30, DriverQualificationEnum.BUS_DRIVER, "testType", 3);

        //when
        when(driverRepo.findById(anyLong())).thenReturn(Optional.empty());
        when(transportRepo.findById(anyLong())).thenReturn(Optional.of(testTransport));
        when(transportService.updateTransport(any(TransportDto.class))).thenReturn(new Bus());

        //then
        boolean actualResult = driverService.addDriverOnTransport(1L, 1L);
        assertFalse(actualResult);

        verify(driverRepo, times(1)).findById(longArgumentCaptor.capture());
        verify(transportRepo, times(1)).findById(longArgumentCaptor.capture());
        verify(transportService, times(0)).updateTransport(transportDtoArgumentCaptor.capture());
    }

    @Test
    void addDriverOnTransport_inputEmptyTransportAndReturnFalse() {
        //given

        //when
        when(driverRepo.findById(anyLong())).thenReturn(Optional.of(testDriver));
        when(transportRepo.findById(anyLong())).thenReturn(Optional.empty());
        when(transportService.updateTransport(any(TransportDto.class))).thenReturn(new Bus());

        //then
        boolean actualResult = driverService.addDriverOnTransport(1L, 1L);
        assertFalse(actualResult);

        verify(driverRepo, times(1)).findById(longArgumentCaptor.capture());
        verify(transportRepo, times(1)).findById(longArgumentCaptor.capture());
        verify(transportService, times(0)).updateTransport(transportDtoArgumentCaptor.capture());
    }

    @Test
    void findAllDriverBySurname_inputNothingAndReturnListOfDriversBySurname() {
        //given

        //when
        when(driverRepo.findDriversBySurname(anyString())).thenReturn(List.of(testDriver));

        //then
        List<Driver> actualList = driverService.findAllDriverBySurname("testSurname");
        assertEquals(List.of(testDriver), actualList);

        verify(driverRepo, times(1)).findDriversBySurname(stringArgumentCaptor.capture());
    }

    @Test
    void findAllDriverOnRoute_inputNumberOfRouteAndReturnListOfDrivers() {
        //given
        testDriver.getRoute().clear();
        testDriver.getTransport().clear();

        testRoute.getDrivers().add(testDriver);

        //when
        when(routeRepo.findById(anyLong())).thenReturn(Optional.of(testRoute));

        //then
        Set<Driver> actualList = driverService.findAllDriverOnRoute(1L);
        assertEquals(Set.of(testDriver).size(), actualList.size());

        verify(routeRepo, times(1)).findById(longArgumentCaptor.capture());
    }

    @Test
    void findAllDriverOnRoute_inputEmptyRouteAndReturnEmptySet() {
        //given

        //when
        when(routeRepo.findById(anyLong())).thenReturn(Optional.empty());

        //then
        Set<Driver> actualList = driverService.findAllDriverOnRoute(1L);
        assertEquals(Collections.emptySet(), actualList);

        verify(routeRepo, times(1)).findById(longArgumentCaptor.capture());
    }

    @Test
    void findAllTransportsWithoutDriver() {
        //given
        Bus testTransport = new Bus(1L, "testBrand", 30, DriverQualificationEnum.BUS_DRIVER, "testType", 3);

        //when
        when(transportRepo.findTransportWithoutDriver()).thenReturn(List.of(testTransport));

        //then
        List<Transport> actualList = driverService.findAllTransportsWithoutDriver();
        assertEquals(List.of(testTransport).size(), actualList.size());

        verify(transportRepo, times(1)).findTransportWithoutDriver();
    }

    @Test
    void findAllDrivers_inputNothingAndReturnListOfDrivers() {
        //given

        //when
        when(driverRepo.findAll()).thenReturn(List.of(testDriver));

        //then
        List<Driver> actualList = driverService.findAllDrivers();
        assertEquals(List.of(testDriver), actualList);

        verify(driverRepo, times(1)).findAll();
    }
}