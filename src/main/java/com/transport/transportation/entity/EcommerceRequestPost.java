package com.transport.transportation.entity;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class EcommerceRequestPost {

    private Integer sourceId;
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

    @NotNull
    private Integer productid;

    private Float cashInHand;

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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }
}
