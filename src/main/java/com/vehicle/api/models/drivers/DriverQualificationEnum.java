package com.vehicle.api.models.drivers;

import javax.persistence.*;

//@Entity
//@Table (name = "qualifications")
public enum DriverQualificationEnum {

    BUS_DRIVER(),
    TRAM_DRIVER();

    //@Id
    //@Column (name = "qualification_id")
    private Long id;

    //@OneToOne (mapped)
//    @ManyToOne
//    @JoinColumn (name = "type_qualification")
    //@OneToOne (mappedBy = "driverQualificationEnum")
    private String qualificationType;

    public String getQualificationType() {
        return qualificationType;
    }

    public void setQualificationType(String qualificationType) {
        this.qualificationType = qualificationType;
    }

    @Override
    public String toString() {
        return "DriverQualificationEnum{" +
                "id=" + id +
                ", qualificationType='" + qualificationType + '\'' +
                '}';
    }
}
