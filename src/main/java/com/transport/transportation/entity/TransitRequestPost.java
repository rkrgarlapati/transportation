package com.transport.transportation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@MappedSuperclass
public class TransitRequestPost {

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "sourceid")
    @NotNull
    private Source sour;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "destinationid")
    @NotNull
    private Destination dest;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "productid")
    private TransitProducts prod;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "serviceid")
    private TransitServices servi;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "username")
    @NotNull
    private SignUp user;

    @Transient
    @JoinColumn(insertable = false, updatable = false)
    @Column(name = "SOURCEID")
    @JsonProperty
    private Integer sourceId;

    @Transient
    @JoinColumn(insertable = false, updatable = false)
    @Column(name = "DESTINATIONID")
    @JsonProperty
    private Integer destinationId;

    @Transient
    @JoinColumn(insertable = false, updatable = false)
    @Column(name = "PRODUCTID")
    @JsonProperty
    private Integer productId;

    @Transient
    @JoinColumn(insertable = false, updatable = false)
    @Column(name = "SERVICEID")
    @JsonProperty
    private Integer serviceId;

    @Transient
    @JoinColumn(insertable = false, updatable = false)
    @Column(name = "username")
    @JsonProperty
    private String username;

    @Column(name = "COST")
    private double cost;

    @NotNull
    @Column(name = "DATETIME")
    private Date dateTime;

    @Column(name = "REQUESTSTATUS")
    private String requestStatus;

    @Column(name = "usertype")
    private String userType;

    @NotNull
    private Double trucksize;

    @NotNull
    private Boolean customclear;

    @Lob
    private byte[] customsDoc;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @JsonIgnore
    public Integer getSourceId() {
        return sourceId;
    }

    @JsonProperty
    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    @JsonIgnore
    public Integer getDestinationId() {
        return destinationId;
    }

    @JsonProperty
    public void setDestinationId(Integer destinationId) {
        this.destinationId = destinationId;
    }

    public SignUp getUser() {
        return user;
    }

    public void setUser(SignUp user) {
        this.user = user;
    }

    @JsonIgnore
    public String getUsername() {
        return username;
    }

    @JsonProperty
    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Source getSour() {
        return sour;
    }

    public void setSour(Source sour) {
        this.sour = sour;
    }

    public Destination getDest() {
        return dest;
    }

    public void setDest(Destination dest) {
        this.dest = dest;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public TransitProducts getProd() {
        return prod;
    }

    public void setProd(TransitProducts prod) {
        this.prod = prod;
    }

    public TransitServices getServi() {
        return servi;
    }

    public void setServi(TransitServices servi) {
        this.servi = servi;
    }

    @JsonIgnore
    public Integer getProductId() {
        return productId;
    }

    @JsonProperty
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @JsonIgnore
    public Integer getServiceId() {
        return serviceId;
    }

    @JsonProperty
    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Double getTrucksize() {
        return trucksize;
    }

    public void setTrucksize(Double trucksize) {
        this.trucksize = trucksize;
    }

    public Boolean getCustomclear() {
        return customclear;
    }

    public void setCustomclear(Boolean customClear) {
        this.customclear = customclear;
    }

    public byte[] getCustomsDoc() {
        return customsDoc;
    }

    public void setCustomsDoc(byte[] customsDoc) {
        this.customsDoc = customsDoc;
    }
}
