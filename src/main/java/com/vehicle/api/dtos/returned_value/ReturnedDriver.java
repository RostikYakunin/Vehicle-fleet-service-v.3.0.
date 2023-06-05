package com.vehicle.api.dtos.returned_value;

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

    public ReturnedDriver() {
    }

    public ReturnedDriver(String nameOfDriver, String surnameOfDriver, String phoneNumber, String qualificationEnum) {
        this.nameOfDriver = nameOfDriver;
        this.surnameOfDriver = surnameOfDriver;
        this.phoneNumber = phoneNumber;
        this.qualificationEnum = qualificationEnum;
    }
}
