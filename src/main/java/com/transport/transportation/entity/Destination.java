package com.transport.transportation.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "DESTINATION")
public class Destination extends DestinationName{

    @Id
    @Column(name = "DESTINATIONID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer destinationid;

    public Integer getDestinationid() {
        return destinationid;
    }

    public void setDestinationid(Integer destinationid) {
        this.destinationid = destinationid;
    }
}
