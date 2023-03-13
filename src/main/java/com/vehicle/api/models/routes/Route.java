package com.vehicle.api.models.routes;

import com.vehicle.api.models.drivers.Driver;
import com.vehicle.api.models.transports.Transport;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table (name = "routes")
public class Route {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "Error, start way cannot be empty")
    private String startOfWay;
    @NotBlank (message = "Error, end way cannot be empty")
    private String endOfWay;

    @OneToMany (mappedBy = "route")
    private Set<Transport> transports;

    @OneToMany (mappedBy = "d_route")
    private Set<Driver> drivers;

    @Override
    public String toString() {
        return "\nRoute ID  " + id + ", start: "+startOfWay+ ", end: " + endOfWay;
    }
}
