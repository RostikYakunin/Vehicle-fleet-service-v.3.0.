package com.vehicle.api.repos;

import com.vehicle.api.models.drivers.Driver;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepoI extends CrudRepository<Driver, Long> {

    @Query("SELECT d FROM Driver d WHERE d.surnameOfDriver = :surname")
    List<Driver> findDriversBySurname(@Param("surname") String surname);

}
