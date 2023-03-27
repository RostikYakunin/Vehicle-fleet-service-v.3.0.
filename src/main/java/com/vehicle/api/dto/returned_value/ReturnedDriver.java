package com.vehicle.api.dto.returned_value;

import com.vehicle.api.models.drivers.DriverQualificationEnum;
import com.vehicle.api.models.routes.Route;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ReturnedDriver {
    private Long id;

    private String nameOfDriver;

    private String surnameOfDriver;

    private String phoneNumber;

    private String qualificationEnum;

    private Set<Long> transportId = new HashSet<>();

    private Long routeId;
}
