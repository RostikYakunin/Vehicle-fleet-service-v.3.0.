package com.vehicle.api.models.drivers;

import com.vehicle.api.models.routes.Route;
import com.vehicle.api.models.transports.Transport;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "drivers")
public class Driver {
    @Id
    @Column(name = "driver_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "qualification")
    private DriverQualificationEnum qualificationEnum;

    @ManyToMany(mappedBy = "drivers")
    private Set<Transport> transport = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "drivers_routes",
            joinColumns = @JoinColumn(name = "driver_id"),
            inverseJoinColumns = @JoinColumn(name = "route_id")
    )
    private Set<Route> route = new HashSet<>();

    public Driver() {
    }

    public Driver(String nameOfDriver, String surnameOfDriver, String phoneNumber) {
        this.nameOfDriver = nameOfDriver;
        this.surnameOfDriver = surnameOfDriver;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "\nDriver ID = " + id + ", name: " + nameOfDriver + ", surname: " + surnameOfDriver + ", phone number: " + phoneNumber +
                ", qualification: " + this.getQualificationEnum();
    }
}
