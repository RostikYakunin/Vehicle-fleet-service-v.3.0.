package com.vehicle.api.repos;

import com.vehicle.api.models.routes.Route;
import org.springframework.data.repository.CrudRepository;

public interface RouteRepoI extends CrudRepository <Route, Long> {
}
