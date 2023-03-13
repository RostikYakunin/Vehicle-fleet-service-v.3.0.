package com.vehicle.api.servises;


import com.vehicle.api.dto.DriverDtoUpdate;
import com.vehicle.api.models.drivers.Driver;
import com.vehicle.api.models.routes.Route;
import com.vehicle.api.models.transports.Transport;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public interface DriverServiceI {

    Driver addDriver(Driver driver);

    Optional<Driver> findDriverById(Long id);

    Driver updateDriver(DriverDtoUpdate driver);

    boolean deleteDriverById(Long id);

    boolean addDriverOnTransport(Driver driver, Transport transport);

    List<Driver> findAllDriverBySurname(String surname);

    Set<Driver> findAllDriverOnRoute(Route route);

    List<Transport> findAllTransportsWithoutDriver();

    List<Driver> findAllDrivers();

}
