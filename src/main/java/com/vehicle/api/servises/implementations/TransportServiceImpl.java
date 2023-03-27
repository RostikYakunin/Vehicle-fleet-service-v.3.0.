package com.vehicle.api.servises.implementations;

import com.vehicle.api.dto.TransportDto;
import com.vehicle.api.dto.handler.RouteDtoHandler;
import com.vehicle.api.dto.handler.TransportDtoHandler;
import com.vehicle.api.models.routes.Route;
import com.vehicle.api.models.transports.Transport;
import com.vehicle.api.models.transports.inheritors.Bus;
import com.vehicle.api.models.transports.inheritors.Tram;
import com.vehicle.api.repos.DriverRepoI;
import com.vehicle.api.repos.RouteRepoI;
import com.vehicle.api.repos.TransportRepoI;
import com.vehicle.api.servises.RouteServiceI;
import com.vehicle.api.servises.TransportServiceI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TransportServiceImpl implements TransportServiceI {

    private final DriverRepoI driverRepo;
    private final TransportRepoI transportRepo;
    private final RouteRepoI routeRepo;
    private final RouteServiceI routeService;

    @Autowired
    public TransportServiceImpl(DriverRepoI driverRepo, TransportRepoI transportRepo, RouteRepoI routeRepo,
                                RouteServiceI routeService) {
        this.driverRepo = driverRepo;
        this.transportRepo = transportRepo;
        this.routeRepo = routeRepo;
        this.routeService = routeService;
    }

    @Override
    public Transport addTransport(TransportDto transportDto) {
        if (transportDto.getDriverQualificationEnum().equalsIgnoreCase("bus")) {
            Bus bus = (Bus) TransportDtoHandler.mappingDtoToTransportMethodAdd(transportDto);
            log.info("Transport was added to db " + bus);

            return transportRepo.save(bus);
        } else {
            Tram tram = (Tram) TransportDtoHandler.mappingDtoToTransportMethodAdd(transportDto);
            log.info("Transport was added to db " + tram);

            return transportRepo.save(tram);
        }
    }

    @Override
    public Optional<Transport> findTransportById(Long id) {
        if (transportRepo.findById(id).isEmpty()) {
            log.warn("Error, transport with id = " + id + " not found");
            return Optional.empty();
        }

        return transportRepo.findById(id);
    }

    @Override
    public Transport updateTransport(TransportDto transportDto) {
        Optional<Transport> foundTransport = transportRepo.findById(transportDto.getId());

        Bus bus;
        Tram tram;

        if (transportDto.getDriverQualificationEnum().equalsIgnoreCase("bus")) {
            bus = (Bus) TransportDtoHandler.mappingDtoToTransportMethodUpdate(transportDto, foundTransport);
            return bus;
        } else {
            tram = (Tram) TransportDtoHandler.mappingDtoToTransportMethodUpdate(transportDto, foundTransport);
            return tram;
        }
    }

    @Override
    public boolean deleteTransport(Long id) {
        if (transportRepo.findById(id).isEmpty()) {
            log.warn("Error, transport with id = " + id + " not found");
            return false;
        }

        if (!transportRepo.findById(id).get().getDrivers().isEmpty()) {
            log.warn("This transport can`t be deleted, transport has the driver = " + transportRepo.findById(id).get().getDrivers());
            return false;
        }

        log.info("Transport with id= " + id + " was deleted");
        transportRepo.deleteById(id);
        return true;
    }

    @Override
    public List<Transport> findAllTransports() {
        return (List<Transport>) transportRepo.findAll();
    }

    @Override
    public List<Transport> findTransportByBrand(String brand) {
        return transportRepo.findTransportByBrand(brand);
    }

    @Override
    public List<Transport> findTransportWithoutDriver() {
        return transportRepo.findTransportWithoutDriver();
    }

    @Override
    public boolean addTransportToRoute(long transportId, long routeId) {
        if (transportRepo.findById(transportId).isEmpty() || routeRepo.findById(routeId).isEmpty()) {
            log.warn("Transport or route cannot be null");
            return false;
        }

        Route route = routeRepo.findById(routeId).get();
        Transport transport = transportRepo.findById(transportId).get();

        route.getTransports().add(transport);

        log.info("On route " + route + " was added transport " + transport);
        routeService.updateRoute(RouteDtoHandler.createRouteDto(route));
        log.info("Route was update " + route);
        return true;
    }
    @Override
    public boolean removeTransportFromRoute(long transportId, long routeId) {
        if (transportRepo.findById(transportId).isEmpty() || routeRepo.findById(routeId).isEmpty()) {
            log.warn("Transport or route cannot be null");
            return false;
        }

        Transport transport = transportRepo.findById(transportId).get();
        Route route = routeRepo.findById(routeId).get();

        for (Transport tr : route.getTransports()) {
            if (tr.equals(transport)) {
                tr = null;
                log.info("Transport " + transport + " was deleted from route " + route);
                routeService.updateRoute(RouteDtoHandler.createRouteDto(route));
                return true;
            } else {
                log.warn("Transport " + transport + " not found on route " + route);
                return false;
            }
        }

        return false;
    }
}
