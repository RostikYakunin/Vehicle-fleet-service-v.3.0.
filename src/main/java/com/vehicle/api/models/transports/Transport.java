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
    //@Column (name = "transport_id")
    private Long id;

    @NotBlank (message = "Error, transport`s brand cannot be empty")
    @Column (name = "brand_of_transport")
    private String brandOfTransport;

    @NotBlank (message = "Error, passenger`s amount brand cannot be empty")
    @Column (name = "amount_of_passengers")
    private Integer amountOfPassengers;

    @OneToOne (mappedBy = "transport")
    private Driver driver;

    @ManyToOne
    @JoinColumn (name = "route_id")
    private Route route;

    @NotBlank (message = "Error, driver`s qualification cannot be empty")
    //@Column (name = "driver_qualification")
    //@OneToOne
   // @JoinColumn (name = "qualification_type")
    //@Enumerated (EnumType.ORDINAL)
    private DriverQualificationEnum driverQualificationEnum;

    public Transport() {
    }

    @Override
    public String toString() {
        return "\nTransport ID " + id + ", model: " + brandOfTransport + ", numbers of passengers: " + amountOfPassengers +
                ", driver qualification: " + driverQualificationEnum.name();
    }
}
