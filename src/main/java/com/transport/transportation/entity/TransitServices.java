package com.transport.transportation.entity;

import javax.persistence.*;

@Entity
@Table(name = "TRANSITSERVICES")
public class TransitServices extends TransitServiceName {

    @Id
    @Column(name = "SERVICEID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer serviceid;

    public Integer getServiceid() {
        return serviceid;
    }

    public void setServiceid(Integer serviceid) {
        this.serviceid = serviceid;
    }
}
