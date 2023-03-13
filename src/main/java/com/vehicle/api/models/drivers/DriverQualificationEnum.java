package com.vehicle.api.models.drivers;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "qualifications")
public enum DriverQualificationEnum {

    BUS_DRIVER("BUS"),
    TRAM_DRIVER("TRAM");

    DriverQualificationEnum (String type) {
        this.type = type;
    }

    @Id
    @Column (name = "qualification_id")
    private Long id;

    @Column (name = "type")
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
