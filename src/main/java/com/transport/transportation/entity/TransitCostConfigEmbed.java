package com.transport.transportation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
public class TransitCostConfigEmbed implements Serializable {

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "sourceid")
    @NotNull
    private TransitSource source;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "destinationid")
    @NotNull
    private TransitDestination destination;

    public TransitSource getSource() {
        return source;
    }

    public void setSource(TransitSource source) {
        this.source = source;
    }

    public TransitDestination getDestination() {
        return destination;
    }

    public void setDestination(TransitDestination destination) {
        this.destination = destination;
    }
}
