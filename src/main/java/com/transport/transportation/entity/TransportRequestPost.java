package com.transport.transportation.entity;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class TransportRequestPost {

    @NotNull
    private Integer sourceId;

    @NotNull
    private Integer destinationId;

    @NotNull
    private String email;

    @NotNull
    private String userType;

    @NotNull
    private double cost;

    @NotNull
    private String mobileNo;

    @NotNull
    private Date dateTime;

    private Float cashInHand;

    private int multipleTrips;

    private int roundTrips;

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public Integer getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(Integer destinationId) {
        this.destinationId = destinationId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
