package com.vehicle.api.services.interfaces;

import com.vehicle.api.dtos.dto.TransportDto;
import com.vehicle.api.models.transports.Transport;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TransportServiceI {

    Transport addTransport(TransportDto transportDto);

    Optional<Transport> findTransportById(Long id);

    Transport updateTransport(TransportDto transportDto);

    boolean deleteTransportById(Long id);

    List<Transport> findAllTransports();

    List<Transport> findTransportByBrand(String brand);

    List<Transport> findTransportWithoutDriver();

    boolean addTransportToRoute(long transportId, long routeId);

    boolean removeTransportFromRoute(long transportId, long routeId);
}
