package com.vehicle.api.mediators.returned_value;

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

    private Set<Long> routeId = new HashSet<>();
}
