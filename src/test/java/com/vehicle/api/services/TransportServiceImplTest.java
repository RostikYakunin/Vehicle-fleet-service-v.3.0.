package com.vehicle.api.services;

import com.vehicle.api.dtos.dto.TransportDto;
import com.vehicle.api.models.drivers.Driver;
import com.vehicle.api.models.drivers.DriverQualificationEnum;
import com.vehicle.api.models.routes.Route;
import com.vehicle.api.models.transports.Transport;
import com.vehicle.api.models.transports.inheritors.Bus;
import com.vehicle.api.models.transports.inheritors.Tram;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class TransportServiceImplTest {

    @Autowired
    TransportServiceImpl transportService;

    @MockBean
    TransportRepoI transportRepo;

    @MockBean
    RouteRepoI routeRepo;

    @Captor
    ArgumentCaptor<Long> longArgumentCaptor;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    @Captor
    ArgumentCaptor<Transport> transportArgumentCaptor;

    Bus testBus;

    Tram testTram;

    TransportDto testBusDto;

    TransportDto testTramDto;

    @BeforeEach
    void setUp() {
        Route route = new Route(1L);
        Driver driver = new Driver(1L);

        testBus = new Bus(1L, "testBus", 30, DriverQualificationEnum.BUS_DRIVER, "testType", 3);
        testBus.getDrivers().add(driver);
        testBus.getRoute().add(route);

        testTram = new Tram(1L, "testTram", 40, DriverQualificationEnum.TRAM_DRIVER, 4);
        testTram.getDrivers().add(driver);
        testTram.getRoute().add(route);

        testBusDto = new TransportDto(1L, "testBus", 30, "bus", "testType", 3);
        testBusDto.getDrivers().add(driver);
        testBusDto.getRoutes().add(route);

        testTramDto = new TransportDto(1L, "testTram", 40, "tram", 4);
        testTramDto.getDrivers().add(driver);
        testTramDto.getRoutes().add(route);
    }

    @Test
    void addTransport_inputBusDtoReturnBus() {
        //given

        //when
        when(transportRepo.save(any(Transport.class))).thenReturn(testBus);

        //then
        Bus actualBus = (Bus) transportService.addTransport(testBusDto);
        assertEquals(testBus, actualBus);

        verify(transportRepo, times(1)).save(transportArgumentCaptor.capture());
    }

    @Test
    void addTransport_inputTramDtoReturnTram() {
        //given

        //when
        when(transportRepo.save(any(Transport.class))).thenReturn(testTram);

        //then
        Tram actualTram = (Tram) transportService.addTransport(testTramDto);
        assertEquals(testTram, actualTram);

        verify(transportRepo, times(1)).save(transportArgumentCaptor.capture());
    }

    @Test
    void findTransportById_inputLongReturnOptionalOfBus() {
        //given

        //when
        when(transportRepo.findById(anyLong())).thenReturn(Optional.of(testBus));

        //then
        Optional<Transport> actualTransport = transportService.findTransportById(1L);
        assertEquals(Optional.of(testBus), actualTransport);

        verify(transportRepo, times(1)).findById(longArgumentCaptor.capture());
    }

    @Test
    void findTransportById_inputLongReturnOptionalOfTram() {
        //given

        //when
        when(transportRepo.findById(anyLong())).thenReturn(Optional.of(testTram));

        //then
        Optional<Transport> actualTransport = transportService.findTransportById(1L);
        assertEquals(Optional.of(testTram), actualTransport);

        verify(transportRepo, times(1)).findById(longArgumentCaptor.capture());
    }

    @Test
    void findTransportById_inputEmptyReturnOptionalEmpty() {
        //given

        //when
        when(transportRepo.findById(anyLong())).thenReturn(Optional.empty());

        //then
        Optional<Transport> actualTransport = transportService.findTransportById(1L);
        assertEquals(Optional.empty(), actualTransport);

        verify(transportRepo, times(1)).findById(longArgumentCaptor.capture());
    }

    @Test
    void updateTransport_inputBusDtoReturnBus() {
        //given

        //when
        when(transportRepo.findById(anyLong())).thenReturn(Optional.of(testBus));
        when(transportRepo.save(any(Transport.class))).thenReturn(testBus);

        //then
        Bus actualBus = (Bus) transportService.updateTransport(testBusDto);
        assertEquals(testBus, actualBus);

        verify(transportRepo, times(1)).findById(longArgumentCaptor.capture());
        verify(transportRepo, times(1)).save(transportArgumentCaptor.capture());
    }

    @Test
    void updateTransport_inputTramDtoReturnTram() {
        //given

        //when
        when(transportRepo.findById(anyLong())).thenReturn(Optional.of(testTram));
        when(transportRepo.save(any(Transport.class))).thenReturn(testTram);

        //then
        Tram actualTram = (Tram) transportService.updateTransport(testTramDto);
        assertEquals(testTram, actualTram);

        verify(transportRepo, times(1)).findById(longArgumentCaptor.capture());
        verify(transportRepo, times(1)).save(transportArgumentCaptor.capture());
    }

    @Test
    void deleteTransportById_inputLongAndReturnTrue() {
        //given
        testBus.getDrivers().clear();

        //when
        when(transportRepo.findById(anyLong())).thenReturn(Optional.of(testBus));

        //then
        boolean actualResult = transportService.deleteTransportById(1L);
        assertTrue(actualResult);

        verify(transportRepo, times(1)).findById(longArgumentCaptor.capture());
        verify(transportRepo, times(1)).deleteById(longArgumentCaptor.capture());
    }

    @Test
    void deleteTransportById_inputEmptyTransportAndReturnFalse() {
        //given

        //when
        when(transportRepo.findById(anyLong())).thenReturn(Optional.empty());

        //then
        boolean actualResult = transportService.deleteTransportById(1L);
        assertFalse(actualResult);

        verify(transportRepo, times(1)).findById(longArgumentCaptor.capture());
        verify(transportRepo, times(0)).deleteById(longArgumentCaptor.capture());
    }

    @Test
    void deleteTransportById_inputTransportWithNotEmptyDriversAndReturnFalse() {
        //given

        //when
        when(transportRepo.findById(anyLong())).thenReturn(Optional.of(testBus));

        //then
        boolean actualResult = transportService.deleteTransportById(1L);
        assertFalse(actualResult);

        verify(transportRepo, times(1)).findById(longArgumentCaptor.capture());
        verify(transportRepo, times(0)).deleteById(longArgumentCaptor.capture());
    }

    @Test
    void findAllTransports() {
        //given

        //when
        when(transportRepo.findAll()).thenReturn(List.of(testBus));

        //then
        List<Transport> actualList = transportService.findAllTransports();
        assertEquals(List.of(testBus), actualList);

        verify(transportRepo, times(1)).findAll();
    }

    @Test
    void findTransportByBrand_inputNothingAndReturnListOfTransportsByBrand() {
        //given

        //when
        when(transportRepo.findTransportByBrand(anyString())).thenReturn(List.of(testBus));

        //then
        List<Transport> actualList = transportService.findTransportByBrand("testBus");
        assertEquals(List.of(testBus).size(), actualList.size());

        verify(transportRepo, times(1)).findTransportByBrand(stringArgumentCaptor.capture());
    }

    @Test
    void findTransportWithoutDriver_inputNothingAndReturnListOfTransportsWithoutDriver() {
        //given
        testBus.getDrivers().clear();

        //when
        when(transportRepo.findTransportWithoutDriver()).thenReturn(List.of(testBus));

        //then
        List<Transport> actualList = transportService.findTransportWithoutDriver();
        assertEquals(List.of(testBus).size(), actualList.size());
        assertEquals(Collections.emptySet(), actualList.get(0).getDrivers());

        verify(transportRepo, times(1)).findTransportWithoutDriver();
    }

    @Test
    void addTransportToRoute_inputTransportAndRouteReturnTrue() {
        //given
        Route testRoute = new Route(1L, "testStart", "testEnd");

        //when
        when(transportRepo.findById(anyLong())).thenReturn(Optional.of(testBus));
        when(routeRepo.findById(anyLong())).thenReturn(Optional.of(testRoute));
        when(transportRepo.save(any(Transport.class))).thenReturn(testBus);

        //then
        boolean actualResult = transportService.addTransportToRoute(1L, 1L);
        assertTrue(actualResult);

        verify(transportRepo, times(2)).findById(longArgumentCaptor.capture());
        verify(routeRepo, times(1)).findById(longArgumentCaptor.capture());
        verify(transportRepo, times(1)).save(transportArgumentCaptor.capture());
    }

    @Test
    void addTransportToRoute_inputEmptyTransportReturnFalse() {
        //given
        Route testRoute = new Route(1L, "testStart", "testEnd");

        //when
        when(transportRepo.findById(anyLong())).thenReturn(Optional.empty());
        when(routeRepo.findById(anyLong())).thenReturn(Optional.of(testRoute));
        when(transportRepo.save(any(Transport.class))).thenReturn(testBus);

        //then
        boolean actualResult = transportService.addTransportToRoute(1L, 1L);
        assertFalse(actualResult);

        verify(transportRepo, times(1)).findById(longArgumentCaptor.capture());
        verify(routeRepo, times(1)).findById(longArgumentCaptor.capture());
        verify(transportRepo, times(0)).save(transportArgumentCaptor.capture());
    }

    @Test
    void removeTransportFromRoute_inputTransportAndRouteReturnTrue() {
        //given
        Route testRoute = new Route(1L, "testStart", "testEnd");

        //when
        when(transportRepo.findById(anyLong())).thenReturn(Optional.of(testBus));
        when(routeRepo.findById(anyLong())).thenReturn(Optional.of(testRoute));
        when(transportRepo.save(any(Transport.class))).thenReturn(testBus);

        //then
        boolean actualResult = transportService.removeTransportFromRoute(1L, 1L);
        assertTrue(actualResult);

        verify(transportRepo, times(2)).findById(longArgumentCaptor.capture());
        verify(routeRepo, times(1)).findById(longArgumentCaptor.capture());
        verify(transportRepo, times(1)).save(transportArgumentCaptor.capture());
    }

    @Test
    void removeTransportFromRoute_inputEmptyTransportReturnFalse() {
        //given
        Route testRoute = new Route(1L, "testStart", "testEnd");

        //when
        when(transportRepo.findById(anyLong())).thenReturn(Optional.empty());
        when(routeRepo.findById(anyLong())).thenReturn(Optional.of(testRoute));
        when(transportRepo.save(any(Transport.class))).thenReturn(testBus);

        //then
        boolean actualResult = transportService.removeTransportFromRoute(1L, 1L);
        assertFalse(actualResult);

        verify(transportRepo, times(1)).findById(longArgumentCaptor.capture());
        verify(routeRepo, times(1)).findById(longArgumentCaptor.capture());
        verify(transportRepo, times(0)).save(transportArgumentCaptor.capture());
    }
}