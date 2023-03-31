package com.vehicle.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vehicle.api.mediators.dto.RouteDto;
import com.vehicle.api.models.routes.Route;
import com.vehicle.api.services.interfaces.RouteServiceI;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest (RouteController.class)
class RouteControllerTest {

    @MockBean
    RouteServiceI routeService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createRoute() {
        when(routeService.addRoute(any(RouteDto.class))).thenReturn(new Route());
    }

    @Test
    void findRouteById() {
    }

    @Test
    void updateRoute() {
    }

    @Test
    void deleteRouteById() {
    }

    @Test
    void findAllRoutes() {
    }

    @Test
    void findAllRoutesWithoutTransport() {
    }
}