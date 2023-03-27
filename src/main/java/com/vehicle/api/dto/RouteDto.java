package com.vehicle.api.dto;

import com.vehicle.api.models.drivers.Driver;
import com.vehicle.api.models.transports.Transport;
import lombok.Data;

import java.util.Set;

@Data
public class RouteDto {

    private long id;
    private String startOfWay;
    private String endOfWay;

    private Set<Transport> transports;

    private Set<Driver> drivers;

    public RouteDto() {
    }

    public RouteDto(long id, String startOfWay, String endOfWay) {
        this.id = id;
        this.startOfWay = startOfWay;
        this.endOfWay = endOfWay;
    }
}
