package com.vehicle.api.models.transports;

import com.vehicle.api.models.drivers.Driver;
import com.vehicle.api.models.drivers.DriverQualificationEnum;
import com.vehicle.api.models.routes.Route;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@Table(name = "transports")
public abstract class Transport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank (message = "Error, transport`s brand cannot be empty")
    private String brandOfTransport;

    @NotBlank (message = "Error, passenger`s amount brand cannot be empty")
    private Integer amountOfPassengers;

    @OneToOne (mappedBy = "transport")
    private Driver driver;

    @ManyToOne
    @JoinColumn (name = "route_id")
    private Route route;

    @NotBlank (message = "Error, driver`s qualification cannot be empty")
    private DriverQualificationEnum driverQualificationEnum;

    public Transport() {
    }

    @Override
    public String toString() {
        return "\nTransport ID " + id + ", model: " + brandOfTransport + ", numbers of passengers: " + amountOfPassengers +
                ", driver qualification: " + driverQualificationEnum.name();
    }
}
