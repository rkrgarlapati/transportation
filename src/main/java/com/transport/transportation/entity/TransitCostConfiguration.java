package com.transport.transportation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TRANSITCOSTCONFIGURATION")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TransitCostConfiguration {

    @EmbeddedId
    @JsonIgnore
    private TransitCostConfigEmbed costConfigEmbed;

    @NotNull
    private double cost;

    @JoinColumn(insertable = false, updatable = false)
    @Transient
    private Integer destinationid;

    @JoinColumn(insertable = false, updatable = false)
    @Transient
    private Integer sourceid;

    @JoinColumn(insertable = false, updatable = false)
    @Transient
    private Double trucksize;

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Integer getDestinationid() {
        return destinationid;
    }

    public void setDestinationid(Integer destinationid) {
        this.destinationid = destinationid;
    }

    public Integer getSourceid() {
        return sourceid;
    }

    public void setSourceid(Integer sourceid) {
        this.sourceid = sourceid;
    }

    public TransitCostConfigEmbed getCostConfigEmbed() {
        return costConfigEmbed;
    }

    public void setCostConfigEmbed(TransitCostConfigEmbed costConfigEmbed) {
        this.costConfigEmbed = costConfigEmbed;
    }

    public Double getTrucksize() {
        return trucksize;
    }

    public void setTrucksize(Double trucksize) {
        this.trucksize = trucksize;
    }
}
