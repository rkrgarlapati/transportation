package com.transport.transportation.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public class TransitSourceName {

    @NotNull
    @Column(name = "SOURCENAME", nullable=false)
    private String sourcename;

    public String getSourcename() {
        return sourcename;
    }

    public void setSourcename(String sourcename) {
        this.sourcename = sourcename;
    }
}
