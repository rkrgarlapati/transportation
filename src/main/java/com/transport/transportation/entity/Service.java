package com.transport.transportation.entity;

import javax.persistence.*;

@Entity
@Table(name = "SERVICE")
public class Service extends ServiceName{

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
