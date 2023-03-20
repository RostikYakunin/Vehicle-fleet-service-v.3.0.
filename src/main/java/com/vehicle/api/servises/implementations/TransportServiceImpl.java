package com.vehicle.api.servises.implementations;

import com.vehicle.api.models.routes.Route;
import com.vehicle.api.models.transports.Transport;
import com.vehicle.api.servises.TransportServiceI;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportServiceImpl implements TransportServiceI {
    @Override
    public Transport addTransport(Transport transport) {
        return null;
    }

    @Override
    public Transport findTransportById(Long id) {
        return null;
    }

    @Override
    public Transport updateTransport(Transport transport) {
        return null;
    }

    @Override
    public boolean deleteTransport(Long id) {
        return false;
    }

    @Override
    public List<Transport> findAllTransports() {
        return null;
    }

    @Override
    public List<Transport> findTransportByBrand(String brand) {
        return null;
    }

    @Override
    public List<Transport> findTransportWithoutDriver() {
        return null;
    }

    @Override
    public boolean addTransportToRoute(Transport transport, Route route) {
        return false;
    }

    @Override
    public boolean removeTransportFromRoute(Transport transport, Route route) {
        return false;
    }
}
