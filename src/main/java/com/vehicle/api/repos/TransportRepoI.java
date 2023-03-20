package com.vehicle.api.repos;

import com.vehicle.api.models.transports.Transport;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransportRepoI extends CrudRepository<Transport, Long> {

    @Query("SELECT t FROM Transport t WHERE t.drivers = null")
    List<Transport> findTransportsWithoutDrivers ();
}
