package com.vehicle.api.repos;

import com.vehicle.api.models.routes.Route;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepoI extends CrudRepository <Route, Long> {
}
