package com.vehicle.api.servises;

import com.vehicle.api.models.routes.Route;
import com.vehicle.api.models.transports.Transport;

import java.util.List;

public interface TransportServiceI {

    Transport addTransport(Transport transport);

    Transport findTransportById(Long id);

    Transport updateTransport(Transport transport);

    boolean deleteTransport(Long id);

    List<Transport> findAllTransports();

    List<Transport> findTransportByBrand(String brand);

    List<Transport> findTransportWithoutDriver();

    boolean addTransportToRoute(Transport transport, Route route);

    boolean removeTransportFromRoute(Transport transport, Route route);
}
