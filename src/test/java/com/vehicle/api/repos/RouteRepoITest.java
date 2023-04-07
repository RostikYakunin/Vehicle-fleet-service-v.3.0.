package com.vehicle.api.repos;

import com.vehicle.api.models.routes.Route;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RouteRepoITest {

    @Autowired
    RouteRepoI routeRepo;

    Route testRoute;

    @BeforeEach
    void setUp() {
        testRoute = new Route("testStart", "testEnd");
        routeRepo.save(testRoute);
    }

    @AfterEach
    void tearDown() {
        routeRepo.deleteAll();
    }

    @Test
    void findAllRoutesWithoutTransport_returnsRouteWithoutTransports() {
        //given
        Route expectedRoute = testRoute;

        //when
        Route actualRoute = routeRepo.findAllRoutesWithoutTransport().stream().findFirst().get();
        System.out.println(actualRoute);

        //then
        assertEquals(expectedRoute.getTransports(), actualRoute.getTransports());
    }
}