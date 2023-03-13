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
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "driver_id")
    private Long id;

    @NotBlank (message = "Error, driver`s name cannot be empty")
    @Column (name = "name_of_driver")
    private String nameOfDriver;

    @NotBlank (message = "Error, driver`s surname cannot be empty")
    @Column (name = "surname_of_driver")
    private String surnameOfDriver;

    @NotBlank (message = "Error, phone number cannot be empty")
    @Column (name = "phone_number")
    private String phoneNumber;

    @NotBlank (message = "Error, qualification cannot be empty")
    @Column (name = "qualification")
    private DriverQualificationEnum qualificationEnum;

    @OneToOne (cascade = CascadeType.ALL)
    private Transport transport;

    @ManyToOne
    @JoinColumn (name = "route_id")
    private Route d_route;

    public Driver() {
    }

    @Override
    public String toString() {
        return "\nDriver ID = "+ id + ", name: "+ nameOfDriver + ", surname: "+ surnameOfDriver+ ", phone number: " + phoneNumber +
                ", qualification: " + this.getQualificationEnum().name();
    }

    // TODO: list of actions
    // 1. class driverDto for add action with string qualification
    // 2. handler for dto in driver service or separate class
    // 3. relationship between enum and driver @oneToOne or @oneToMany
}
