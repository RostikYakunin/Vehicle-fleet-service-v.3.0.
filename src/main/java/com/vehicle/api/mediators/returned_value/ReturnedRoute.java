package com.vehicle.api.mediators.returned_value;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ReturnedRoute {
    private long id;

    private String startOfWay;

    private String endOfWay;

    private Set<Long> transportsId = new HashSet<>();

    private Set<Long> driversId = new HashSet<>();
}
