package com.vehicle.api.repos;

import com.vehicle.api.models.routes.Route;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepoI extends CrudRepository<Route, Long> {
    @Query("SELECT r FROM Route r WHERE r.transports is null")
    List<Route> findAllRoutesWithoutTransport();
}
