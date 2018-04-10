package com.transport.transportation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "TRANSPORTREQUEST")
public class TransportRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REQUESTID")
    private int requestId;

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
    @JoinColumn(name = "username")
    @NotNull
    private User user;

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
    @Column(name = "username")
    @JsonProperty
    private String username;

    @NotNull
    @Column(name = "COST")
    private double cost;

    @NotNull
    @Column(name = "MOBILENO")
    private String mobileNo;

    @NotNull
    @Column(name = "DATETIME")
    private Date dateTime;

    @Column(name = "CASHINHAND")
    private Float cashInHand;

    @Column(name = "MULTIPLETRIPS")
    private int multipleTrips;

    @Column(name = "ROUNDTRIPS")
    private int roundTrips;

    @Column(name = "REQUESTSTATUS")
    private String requestStatus;

    @Column(name = "UPDATEDDATE")
    private Date updatedDate;

    @Column(name = "usertype")
    private String userType;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public int getRequestId() {
        return requestId;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Float getCashInHand() {
        return cashInHand;
    }

    public void setCashInHand(Float cashInHand) {
        this.cashInHand = cashInHand;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getMultipleTrips() {
        return multipleTrips;
    }

    public void setMultipleTrips(int multipleTrips) {
        this.multipleTrips = multipleTrips;
    }

    public int getRoundTrips() {
        return roundTrips;
    }

    public void setRoundTrips(int roundTrips) {
        this.roundTrips = roundTrips;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
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
}
