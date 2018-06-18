package com.transport.transportation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "TRANSITREQUEST")
public class TransitRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REQUESTID")
    private int requestId;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "sourceid")
    @NotNull
    private TransitSource sour;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "destinationid")
    @NotNull
    private TransitDestination dest;

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
    @JoinColumn(name = "email")
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

    @Column(name = "COST")
    @NotNull
    private double cost;

    @NotNull
    @Column(name = "DATETIME")
    private Date dateTime;

    @Column(name = "REQUESTSTATUS")
    private String requestStatus;

    /*@Column(name = "usertype")
    private String userType;*/

    @NotNull
    private Double trucksize;

    @NotNull
    @Column(name = "MOBILE")
    private String mobileNo;

    @NotNull
    private Boolean customclear;

    private double warehousecharges;
    @Column(name = "driveremail")
    private String driveremail;

    @Column(name = "otp")
    @JsonIgnore
    private String otp;

    private Double cashinhand;
    /*@Lob
    private byte[] customsdoc;*/

    /*public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }*/

    public String getDriveremail() {
        return driveremail;
    }

    @JsonIgnore
    public void setDriveremail(String driveremail) {
        this.driveremail = driveremail;
    }

    @JsonIgnore
    public String getOtp() {
        return otp;
    }

    @JsonIgnore
    public void setOtp(String otp) {
        this.otp = otp;
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

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public TransitSource getSour() {
        return sour;
    }

    public void setSour(TransitSource sour) {
        this.sour = sour;
    }

    public TransitDestination getDest() {
        return dest;
    }

    public void setDest(TransitDestination dest) {
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
        this.customclear = customClear;
    }

    /*public byte[] getCustomsdoc() {
        return customsdoc;
    }

    public void setCustomsdoc(byte[] customsdoc) {
        this.customsdoc = customsdoc;
    }*/

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public double getWarehousecharges() {
        return warehousecharges;
    }

    public void setWarehousecharges(double warehousecharges) {
        this.warehousecharges = warehousecharges;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Double getCashinhand() {
        return cashinhand;
    }

    public void setCashinhand(Double cashinhand) {
        this.cashinhand = cashinhand;
    }
}
