package com.vehicle.api.models.transports;

import com.vehicle.api.models.drivers.Driver;
import com.vehicle.api.models.drivers.DriverQualificationEnum;
import com.vehicle.api.models.routes.Route;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

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

    @ManyToMany (cascade = CascadeType.ALL)
    @JoinTable (
            name = "transports_drivers",
            joinColumns = @JoinColumn (name = "transport_id"),
            inverseJoinColumns = @JoinColumn (name = "driver_id")
    )
    private Set<Driver> drivers;

    @ManyToOne
    @JoinColumn (name = "route_id")
    private Route route;

    @NotBlank (message = "Error, driver`s qualification cannot be empty")
    @Enumerated (EnumType.STRING)
    private DriverQualificationEnum driverQualificationEnum;

    public Transport() {
    }

    @Override
    public String toString() {
        return "\nTransport ID " + id + ", model: " + brandOfTransport + ", numbers of passengers: " + amountOfPassengers +
                ", driver qualification: " + driverQualificationEnum.name();
    }
}
