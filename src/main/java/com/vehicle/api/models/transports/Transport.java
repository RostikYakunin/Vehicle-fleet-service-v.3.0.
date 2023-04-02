package com.vehicle.api.models.transports;

import com.vehicle.api.models.drivers.Driver;
import com.vehicle.api.models.drivers.DriverQualificationEnum;
import com.vehicle.api.models.routes.Route;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "transports")
public abstract class Transport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Error, transport`s brand cannot be empty")
    @Column(name = "brand_of_transport")
    private String brandOfTransport;

    @Column(name = "amount_of_passengers")
    private Integer amountOfPassengers;
    @Enumerated(EnumType.STRING)
    private DriverQualificationEnum driverQualificationEnum;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "transports_drivers",
            joinColumns = @JoinColumn(name = "transport_id"),
            inverseJoinColumns = @JoinColumn(name = "driver_id")
    )
    private Set<Driver> drivers = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "transports_routes",
            joinColumns = @JoinColumn(name = "transport_id"),
            inverseJoinColumns = @JoinColumn(name = "route_id")
    )
    private Set<Route> route = new HashSet<>();
    public Transport() {
    }

    public Transport(String brandOfTransport, Integer amountOfPassengers, DriverQualificationEnum driverQualificationEnum) {
        this.brandOfTransport = brandOfTransport;
        this.amountOfPassengers = amountOfPassengers;
        this.driverQualificationEnum = driverQualificationEnum;
    }

    public Transport(Long id, String brandOfTransport, Integer amountOfPassengers, DriverQualificationEnum driverQualificationEnum) {
        this.id = id;
        this.brandOfTransport = brandOfTransport;
        this.amountOfPassengers = amountOfPassengers;
        this.driverQualificationEnum = driverQualificationEnum;
    }

    @Override
    public String toString() {
        return "\nTransport ID " + id + ", model: " + brandOfTransport + ", numbers of passengers: " + amountOfPassengers +
                ", driver qualification: " + driverQualificationEnum.name();
    }
}
