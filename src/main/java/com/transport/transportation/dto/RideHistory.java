package com.transport.transportation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RideHistory {
    private int requestid;
    private String destination;
    private String source;
    private Date dateTime;
    private String mobileNo;
    private double cost;
    private String traveltype;

    private String productname;
    private Float productprice;
    private Integer productid;

   public int getRequestid() {
        return requestid;
    }

    public void setRequestid(int requestid) {
        this.requestid = requestid;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getTraveltype() {
        return traveltype;
    }

    public void setTraveltype(String traveltype) {
        this.traveltype = traveltype;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public Float getProductprice() {
        return productprice;
    }

    public void setProductprice(Float productprice) {
        this.productprice = productprice;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }
}
