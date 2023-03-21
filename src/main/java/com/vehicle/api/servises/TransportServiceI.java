package com.vehicle.api.servises;

import com.vehicle.api.dto.TransportDto;
import com.vehicle.api.models.routes.Route;
import com.vehicle.api.models.transports.Transport;

import java.util.List;
import java.util.Optional;

public interface TransportServiceI {

    Transport addTransport(TransportDto transportDto);

    Optional<Transport> findTransportById(Long id);

    Transport updateTransport(TransportDto transportDto);

    boolean deleteTransport(Long id);

    List<Transport> findAllTransports();

    List<Transport> findTransportByBrand(String brand);

    List<Transport> findTransportWithoutDriver();

    boolean addTransportToRoute(long transportId, long routeId);

    boolean removeTransportFromRoute(long transportId, long routeId);
}
