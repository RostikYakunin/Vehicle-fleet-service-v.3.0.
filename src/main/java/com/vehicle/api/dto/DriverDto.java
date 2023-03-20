package com.vehicle.api.dto;

import lombok.Data;

@Data
public class DriverDto {

    private Long id;
    private String nameOfDriver;
    private String surnameOfDriver;
    private String phoneNumber;
    private String qualificationEnum;

    public DriverDto() {
    }

    public DriverDto(String nameOfDriver, String surnameOfDriver, String phoneNumber, String qualificationEnum) {
        this.nameOfDriver = nameOfDriver;
        this.surnameOfDriver = surnameOfDriver;
        this.phoneNumber = phoneNumber;
        this.qualificationEnum = qualificationEnum;
    }

}
