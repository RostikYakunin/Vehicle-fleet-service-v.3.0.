package com.vehicle.api.dto;

import com.vehicle.api.models.drivers.DriverQualificationEnum;
import lombok.Data;

@Data
public class DriverDtoUpdate {

    private Long id;

    private String nameOfDriver;

    private String surnameOfDriver;

    private String numberOfPhone;

    private DriverQualificationEnum qualificationEnum;

    public DriverDtoUpdate() {
    }
}
