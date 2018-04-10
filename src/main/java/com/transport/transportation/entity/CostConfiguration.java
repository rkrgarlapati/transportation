package com.transport.transportation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "COSTCONFIGURATION")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CostConfiguration {

    @EmbeddedId
    @JsonIgnore
    private CostConfigEmbed costConfigEmbed;

    @NotNull
    private double cost;

    @JoinColumn(insertable = false, updatable = false)
    @Transient
    private Integer destinationid;

    @JoinColumn(insertable = false, updatable = false)
    @Transient
    private Integer sourceid;

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

    public CostConfigEmbed getCostConfigEmbed() {
        return costConfigEmbed;
    }

    public void setCostConfigEmbed(CostConfigEmbed costConfigEmbed) {
        this.costConfigEmbed = costConfigEmbed;
    }
}
