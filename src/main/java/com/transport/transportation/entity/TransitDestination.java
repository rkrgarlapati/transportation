package com.transport.transportation.entity;

import javax.persistence.*;

@Entity
@Table(name = "TRANSITDESTINATION")
public class TransitDestination extends DestinationName{

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
