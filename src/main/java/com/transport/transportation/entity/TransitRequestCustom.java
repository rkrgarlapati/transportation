package com.transport.transportation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Transient;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransitRequestCustom extends TransitRequest {

    @JsonIgnore
    @Transient
    private String destination;

    @JsonIgnore
    @Transient
    private String source;

    @JsonIgnore
    @Transient
    private String service;

    @JsonIgnore
    @Transient
    private String product;

    @JsonIgnore
    @Transient
    private String userFullName;

    @JsonProperty
    public String getDestination() {
        return destination;
    }

    @JsonIgnore
    public void setDestination(String destination) {
        this.destination = destination;
    }

    @JsonProperty
    public String getSource() {
        return source;
    }

    @JsonIgnore
    public void setSource(String source) {
        this.source = source;
    }

    @JsonProperty
    public String getUserFullName() {
        return userFullName;
    }

    @JsonIgnore
    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    @JsonProperty
    public String getService() {
        return service;
    }

    @JsonIgnore
    public void setService(String service) {
        this.service = service;
    }

    @JsonProperty
    public String getProduct() {
        return product;
    }

    @JsonIgnore
    public void setProduct(String product) {
        this.product = product;
    }
}
