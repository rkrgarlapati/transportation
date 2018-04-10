package com.transport.transportation.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public class DestinationName {

    @NotNull
    @Column(name = "DESTINATIONNAME")
    private String destinationname;

    public String getDestinationname() {
        return destinationname;
    }

    public void setDestinationname(String destinationname) {
        this.destinationname = destinationname;
    }
}
