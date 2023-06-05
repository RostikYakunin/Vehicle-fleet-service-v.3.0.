package com.vehicle.api.dtos.returned_value;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ReturnedRoute {
    private long id;

    public ReturnedRoute() {
    }

    public ReturnedRoute(String startOfWay, String endOfWay) {
        this.startOfWay = startOfWay;
        this.endOfWay = endOfWay;
    }

    private String startOfWay;

    private String endOfWay;

    private Set<Long> transportsId = new HashSet<>();

    private Set<Long> driversId = new HashSet<>();
}
