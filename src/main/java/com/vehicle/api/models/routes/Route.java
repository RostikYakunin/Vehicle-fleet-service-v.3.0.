package com.vehicle.api.models.routes;

import com.vehicle.api.models.drivers.Driver;
import com.vehicle.api.models.transports.Transport;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@Entity
@Table(name = "routes")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Error, start way cannot be empty")
    @Column (name = "start_way")
    private String startOfWay;
    @NotBlank(message = "Error, end way cannot be empty")
    @Column (name = "end_way")
    private String endOfWay;

    @ManyToMany(mappedBy = "route")
    private Set<Transport> transports;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "route")
    private Set<Driver> drivers;

    public Route() {
    }

    public Route(String startOfWay, String endOfWay) {
        this.startOfWay = startOfWay;
        this.endOfWay = endOfWay;
    }

    @Override
    public String toString() {
        return "\nRoute ID  " + id + ", start: " + startOfWay + ", end: " + endOfWay;
    }
}
