package com.vehicle.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vehicle.api.dtos.dto.DriverDto;
import com.vehicle.api.dtos.returned_value.ReturnedDriver;
import com.vehicle.api.dtos.returned_value.ReturnedTransport;
import com.vehicle.api.dtos.returned_value.converter.ReturnedConverter;
import com.vehicle.api.models.drivers.Driver;
import com.vehicle.api.models.drivers.DriverQualificationEnum;
import com.vehicle.api.models.transports.inheritors.Bus;
import com.vehicle.api.services.interfaces.DriverServiceI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DriverController.class)
class DriverControllerTest {

    // beans
    @MockBean
    DriverServiceI driverService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    // captors
    @Captor
    ArgumentCaptor<DriverDto> driverDtoArgumentCaptor;
    @Captor
    ArgumentCaptor<String> surnameArgumentCaptor;
    @Captor
    ArgumentCaptor<Long> id;

    // tested objects
    DriverDto driverDtoTest;
    Driver driverBusTest;
    ReturnedDriver returnedDriverTest;
    Bus bus;
    ReturnedTransport returnedTransport;
    Optional<Driver> optionalDriver;

    @BeforeEach
    void setUp() {
        //given
        driverDtoTest = new DriverDto(
                "testName",
                "testSurname",
                "testPhoneNumber",
                "testQualificationEnum"
        );
        driverDtoTest.setId(1L);

        driverBusTest = new Driver(
                "testName",
                "testSurname",
                "testPhoneNumber",
                DriverQualificationEnum.BUS_DRIVER
        );
        driverBusTest.setId(1L);

        returnedDriverTest = new ReturnedDriver(
                "testName",
                "testSurname",
                "testPhoneNumber",
                "BUS_DRIVER"
        );
        returnedDriverTest.setId(1L);

        optionalDriver = Optional.of(driverBusTest);

        bus = new Bus(
                "testBrand",
                30,
                DriverQualificationEnum.BUS_DRIVER,
                "testType",
                3);
        bus.setId(1L);
        returnedTransport = ReturnedConverter.convertToReturnedTransport(bus);
    }

    @Test
    void post_createNewDriver_returnsObjWithStatus201_Created() throws Exception {
        //given -> see setUp()

        //when
        when(driverService.addDriver(any(DriverDto.class))).thenReturn(driverBusTest);

        //then
        mockMvc.perform(
                        post("/api/drivers")
                                .content(mapper.writeValueAsString(driverDtoTest))
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.nameOfDriver").isString())
                .andExpect(jsonPath("$.surnameOfDriver").isString())
                .andExpect(jsonPath("$.phoneNumber").isString())
                .andExpect(jsonPath("$.qualificationEnum").isString())
                .andExpect(jsonPath("$.id").value(returnedDriverTest.getId()))
                .andExpect(jsonPath("$.nameOfDriver").value(returnedDriverTest.getNameOfDriver()))
                .andExpect(jsonPath("$.surnameOfDriver").value(returnedDriverTest.getSurnameOfDriver()))
                .andExpect(jsonPath("$.phoneNumber").value(returnedDriverTest.getPhoneNumber()))
                .andExpect(jsonPath("$.qualificationEnum").value(returnedDriverTest.getQualificationEnum()));


        verify(driverService, times(1)).addDriver(driverDtoArgumentCaptor.capture());
    }

    @Test
    void get_findDriverById_returnsObjWithStatus200_Ok() throws Exception {
        //given -> see setUp()

        //when
        when(driverService.findDriverById(anyLong())).thenReturn(optionalDriver);

        //then
        mockMvc.perform(
                        get("/api/drivers/1")
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.nameOfDriver").isString())
                .andExpect(jsonPath("$.surnameOfDriver").isString())
                .andExpect(jsonPath("$.phoneNumber").isString())
                .andExpect(jsonPath("$.qualificationEnum").isString())
                .andExpect(jsonPath("$.id").value(returnedDriverTest.getId()))
                .andExpect(jsonPath("$.nameOfDriver").value(returnedDriverTest.getNameOfDriver()))
                .andExpect(jsonPath("$.surnameOfDriver").value(returnedDriverTest.getSurnameOfDriver()))
                .andExpect(jsonPath("$.phoneNumber").value(returnedDriverTest.getPhoneNumber()))
                .andExpect(jsonPath("$.qualificationEnum").value(returnedDriverTest.getQualificationEnum()));

        verify(driverService, times(1)).findDriverById(id.capture());
    }

    @Test
    void put_updateDriver_returnsObjWith200_Ok() throws Exception {
        //given -> see setUp()

        //when
        when(driverService.updateDriver(any(DriverDto.class))).thenReturn(driverBusTest);

        //then
        mockMvc.perform(
                        put("/api/drivers")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(driverDtoTest)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.nameOfDriver").isString())
                .andExpect(jsonPath("$.surnameOfDriver").isString())
                .andExpect(jsonPath("$.phoneNumber").isString())
                .andExpect(jsonPath("$.qualificationEnum").isString())
                .andExpect(jsonPath("$.id").value(returnedDriverTest.getId()))
                .andExpect(jsonPath("$.nameOfDriver").value(returnedDriverTest.getNameOfDriver()))
                .andExpect(jsonPath("$.surnameOfDriver").value(returnedDriverTest.getSurnameOfDriver()))
                .andExpect(jsonPath("$.phoneNumber").value(returnedDriverTest.getPhoneNumber()))
                .andExpect(jsonPath("$.qualificationEnum").value(returnedDriverTest.getQualificationEnum()));

        verify(driverService, times(1)).updateDriver(driverDtoArgumentCaptor.capture());
    }

    @Test
    void delete_deleteDriverById_returnsNothingWith200_Ok() throws Exception {
        //given -> see setUp()

        //when
        when(driverService.deleteDriverById(anyLong())).thenReturn(true);

        //then
        mockMvc.perform(
                        delete("/api/drivers/1")
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        verify(driverService, times(1)).deleteDriverById(id.capture());
    }

    @Test
    void put_addDriverOnTransport_returnNothingWith200_Ok() throws Exception {
        //given -> see setUp()

        //when
        when(driverService.addDriverOnTransport(anyLong(), anyLong())).thenReturn(true);

        //then
        mockMvc.perform(
                        put("/api/drivers/driver_to_transport/1/1")
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        verify(driverService, times(1)).addDriverOnTransport(id.capture(), id.capture());
    }

    @Test
    void get_findAllDriverBySurname_returnsListWith200_Ok() throws Exception {
        //given -> see setUp()

        //when
        when(driverService.findAllDriverBySurname(anyString())).thenReturn(List.of(driverBusTest));

        //then
        mockMvc.perform(
                        get("/api/drivers/surname/surname")
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("surname", "testSurname"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].nameOfDriver").isString())
                .andExpect(jsonPath("$[0].surnameOfDriver").isString())
                .andExpect(jsonPath("$[0].phoneNumber").isString())
                .andExpect(jsonPath("$[0].qualificationEnum").isString())
                .andExpect(jsonPath("$[0].id").value(returnedDriverTest.getId()))
                .andExpect(jsonPath("$[0].nameOfDriver").value(returnedDriverTest.getNameOfDriver()))
                .andExpect(jsonPath("$[0].surnameOfDriver").value(returnedDriverTest.getSurnameOfDriver()))
                .andExpect(jsonPath("$[0].phoneNumber").value(returnedDriverTest.getPhoneNumber()))
                .andExpect(jsonPath("$[0].qualificationEnum").value(returnedDriverTest.getQualificationEnum()));

        verify(driverService, times(1)).findAllDriverBySurname(surnameArgumentCaptor.capture());
    }

    @Test
    void get_findAllDriverOnRoute_returnsSetWith200_Ok() throws Exception {
        //given -> see setUp()

        //when
        when(driverService.findAllDriverOnRoute(anyLong())).thenReturn(Set.of(driverBusTest));

        //then
        mockMvc.perform(
                        get("/api/drivers/drivers_on_route/1")
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].nameOfDriver").isString())
                .andExpect(jsonPath("$[0].surnameOfDriver").isString())
                .andExpect(jsonPath("$[0].phoneNumber").isString())
                .andExpect(jsonPath("$[0].qualificationEnum").isString())
                .andExpect(jsonPath("$[0].id").value(returnedDriverTest.getId()))
                .andExpect(jsonPath("$[0].nameOfDriver").value(returnedDriverTest.getNameOfDriver()))
                .andExpect(jsonPath("$[0].surnameOfDriver").value(returnedDriverTest.getSurnameOfDriver()))
                .andExpect(jsonPath("$[0].phoneNumber").value(returnedDriverTest.getPhoneNumber()))
                .andExpect(jsonPath("$[0].qualificationEnum").value(returnedDriverTest.getQualificationEnum()));

        verify(driverService, times(1)).findAllDriverOnRoute(id.capture());
    }

    @Test
    void get_findAllTransportsWithoutDriver_returnsListWith200_Ok() throws Exception {
        //given -> see setUp()

        //when
        when(driverService.findAllTransportsWithoutDriver()).thenReturn(List.of(bus));

        //then
        mockMvc.perform(
                        get("/api/drivers/transport_without_driver")
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].brandOfTransport").isString())
                .andExpect(jsonPath("$[0].amountOfPassengers").isNumber())
                .andExpect(jsonPath("$[0].driverQualificationEnum").isString())
                .andExpect(jsonPath("$[0].type").isString())
                .andExpect(jsonPath("$[0].amountOfDoors").isNumber())
                .andExpect(jsonPath("$[0].id").value(returnedTransport.getId()))
                .andExpect(jsonPath("$[0].brandOfTransport").value(returnedTransport.getBrandOfTransport()))
                .andExpect(jsonPath("$[0].amountOfPassengers").value(returnedTransport.getAmountOfPassengers()))
                .andExpect(jsonPath("$[0].driverQualificationEnum").value(returnedTransport.getDriverQualificationEnum()))
                .andExpect(jsonPath("$[0].type").value(returnedTransport.getType()))
                .andExpect(jsonPath("$[0].amountOfDoors").value(returnedTransport.getAmountOfDoors()));

        verify(driverService, times(1)).findAllTransportsWithoutDriver();
    }

    @Test
    void get_findAllDrivers_returnsListWith200_Ok() throws Exception {
        //given -> see setUp()

        //when
        when(driverService.findAllDrivers()).thenReturn(List.of(driverBusTest));

        //then
        mockMvc.perform(
                        get("/api/drivers")
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].nameOfDriver").isString())
                .andExpect(jsonPath("$[0].surnameOfDriver").isString())
                .andExpect(jsonPath("$[0].phoneNumber").isString())
                .andExpect(jsonPath("$[0].qualificationEnum").isString())
                .andExpect(jsonPath("$[0].id").value(returnedDriverTest.getId()))
                .andExpect(jsonPath("$[0].nameOfDriver").value(returnedDriverTest.getNameOfDriver()))
                .andExpect(jsonPath("$[0].surnameOfDriver").value(returnedDriverTest.getSurnameOfDriver()))
                .andExpect(jsonPath("$[0].phoneNumber").value(returnedDriverTest.getPhoneNumber()))
                .andExpect(jsonPath("$[0].qualificationEnum").value(returnedDriverTest.getQualificationEnum()));

        verify(driverService, times(1)).findAllDrivers();
    }
}