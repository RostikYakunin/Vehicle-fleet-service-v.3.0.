package com.vehicle.api.repos;

import com.vehicle.api.models.drivers.Driver;
import org.springframework.data.repository.CrudRepository;

public interface DriverRepoI extends CrudRepository <Driver, Long> {
}
