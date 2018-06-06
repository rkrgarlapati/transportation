package com.transport.transportation.entity;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class TransitRequestPost {

    @NotNull
    private Integer sourceId;

    @NotNull
    private Integer destinationId;

    private Integer serviceId;
    private Integer productId;

    @NotNull
    private String userid;

    @NotNull
    private String mobileNo;

    @NotNull
    private double cost;

    /*@NotNull
    private String userType;*/

    @NotNull
    private Date dateTime;

    @NotNull
    private Boolean customclearance;

    @NotNull
    private double trucksize;

    private double warehousecharges;

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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    /*public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }*/

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Boolean isCustomclearance() {
        return customclearance;
    }

    public void setCustomclearance(Boolean customclearance) {
        this.customclearance = customclearance;
    }

    public double getTrucksize() {
        return trucksize;
    }

    public void setTrucksize(double trucksize) {
        this.trucksize = trucksize;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getWarehousecharges() {
        return warehousecharges;
    }

    public void setWarehousecharges(double warehousecharges) {
        this.warehousecharges = warehousecharges;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Boolean getCustomclearance() {
        return customclearance;
    }
}
