package com.vehicle.api.repos;

import com.vehicle.api.models.transports.Transport;
import org.springframework.data.repository.CrudRepository;

public interface TransportRepoI extends CrudRepository<Transport, Long> {
}
