package com.vehicle.api.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RouteDto {

    private long id;

    private String startOfWay;

    private String endOfWay;

    public RouteDto() {
    }

    public RouteDto(long id, String startOfWay, String endOfWay) {
        this.id = id;
        this.startOfWay = startOfWay;
        this.endOfWay = endOfWay;
    }
}
