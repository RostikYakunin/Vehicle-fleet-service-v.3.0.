package com.vehicle.api.dto;

import lombok.Data;

@Data
public class DriverDto {

    private Long id;

    private String nameOfDriver;

    private String surnameOfDriver;

    private String numberOfPhone;

    private String qualificationEnum;

    public DriverDto() {
    }

    public DriverDto(String nameOfDriver, String surnameOfDriver, String numberOfPhone, String qualificationEnum) {
        this.nameOfDriver = nameOfDriver;
        this.surnameOfDriver = surnameOfDriver;
        this.numberOfPhone = numberOfPhone;
        this.qualificationEnum = qualificationEnum;
    }

}
