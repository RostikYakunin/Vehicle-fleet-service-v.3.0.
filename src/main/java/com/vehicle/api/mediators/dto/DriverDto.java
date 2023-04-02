package com.vehicle.api.mediators.dto;

import com.vehicle.api.models.routes.Route;
import com.vehicle.api.models.transports.Transport;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class DriverDto {

    private Long id;
    private String nameOfDriver;
    private String surnameOfDriver;
    private String phoneNumber;
    private String qualificationEnum;

    private Set<Transport> transport = new HashSet<>();

    private Set<Route> route = new HashSet<>();

    public DriverDto() {
    }

    public DriverDto(Long id, String nameOfDriver, String surnameOfDriver, String phoneNumber, String qualificationEnum) {
        this.id = id;
        this.nameOfDriver = nameOfDriver;
        this.surnameOfDriver = surnameOfDriver;
        this.phoneNumber = phoneNumber;
        this.qualificationEnum = qualificationEnum;
    }

    public DriverDto(String nameOfDriver, String surnameOfDriver, String phoneNumber, String qualificationEnum) {
        this.nameOfDriver = nameOfDriver;
        this.surnameOfDriver = surnameOfDriver;
        this.phoneNumber = phoneNumber;
        this.qualificationEnum = qualificationEnum;
    }

}
