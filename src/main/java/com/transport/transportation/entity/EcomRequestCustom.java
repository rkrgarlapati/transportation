package com.transport.transportation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Transient;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EcomRequestCustom extends EcommerceTaxiRequest {

    @JsonIgnore
    @Transient
    private String destination;

    @JsonIgnore
    @Transient
    private String source;

    @JsonIgnore
    @Transient
    private String userFullName;

    @JsonIgnore
    @Transient
    private Integer productid;

    @JsonIgnore
    @Transient
    private String productname;

    @JsonIgnore
    @Transient
    private Float productprice;

    @JsonProperty
    public Integer getProductid() {
        return productid;
    }

    @JsonIgnore
    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    @JsonProperty
    public String getProductname() {
        return productname;
    }

    @JsonIgnore
    public void setProductname(String productname) {
        this.productname = productname;
    }

    @JsonProperty
    public Float getProductprice() {
        return productprice;
    }

    @JsonIgnore
    public void setProductprice(Float productprice) {
        this.productprice = productprice;
    }

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
}
