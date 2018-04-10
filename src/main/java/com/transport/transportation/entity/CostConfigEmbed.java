package com.transport.transportation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
public class CostConfigEmbed implements Serializable {

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "sourceid")
    @NotNull
    private Source source;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "destinationid")
    @NotNull
    private Destination destination;

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }
}
