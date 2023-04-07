package com.vehicle.api.services;

import com.vehicle.api.repos.RouteRepoI;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RouteServiceImplTest {

    @Autowired
    RouteRepoI routeRepo;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addRoute() {
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