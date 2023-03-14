package com.vehicle.api.models.drivers;

import com.vehicle.api.models.routes.Route;
import com.vehicle.api.models.transports.Transport;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "drivers")
public class Driver {
    @Id
    @Column(name = "driver_id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Error, driver`s name cannot be empty")
    @Column(name = "name_of_driver")
    private String nameOfDriver;

    @NotBlank(message = "Error, driver`s surname cannot be empty")
    @Column(name = "surname_of_driver")
    private String surnameOfDriver;

    @NotBlank(message = "Error, phone number cannot be empty")
    @Column(name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private DriverQualificationEnum qualificationEnum;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "transport_id")//
    private Transport transport;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    public Driver() {
    }

    @Override
    public String toString() {
        return "\nDriver ID = " + id + ", name: " + nameOfDriver + ", surname: " + surnameOfDriver + ", phone number: " + phoneNumber +
                ", qualification: " + this.getQualificationEnum();
    }

    // TODO: list of actions
    // 1. class driverDto for add action with string qualification | +
    // 2. handler for dto in driver service or separate class| +
    // 3. relationship between enum and driver @oneToOne or @oneToMany| - don`t touch enum (delete table enum)
    // 4. finish driver service and controller
    // 5. continue following controllers and services
}
