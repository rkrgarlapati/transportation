package com.transport.transportation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "ECOMMERCETAXIREQUEST")
public class EcommerceTaxiRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REQUESTID")
    private int requestid;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "email")
    @NotNull
    private SignUp user;

    @Transient
    @JoinColumn(insertable = false, updatable = false)
    @Column(name = "email")
    @JsonProperty
    private String email;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "sourceid")
    private Source sour;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "destinationid")
    private Destination dest;

    @Transient
    @JoinColumn(insertable = false, updatable = false)
    @Column(name = "SOURCEID")
    @JsonProperty
    private Integer sourceid;

    @Transient
    @JoinColumn(insertable = false, updatable = false)
    @Column(name = "DESTINATIONID")
    @JsonProperty
    private Integer destinationid;

    @NotNull
    @Column(name = "MOBILENO")
    private String mobileNo;

    @NotNull
    @Column(name = "DATETIME")
    private Date dateTime;

    @Column(name = "CASHINHAND")
    private Float cashInHand;

    @Column(name = "REQUESTSTATUS")
    @NotNull
    private String requestStatus;

    @Column(name = "usertype")
    private String userType;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "productid")
    @NotNull
    private Ecommerce ecommerce;

    @Transient
    @JoinColumn(insertable = false, updatable = false)
    @Column(name = "productid")
    @JsonProperty
    private Integer productid;

    @Column(name = "driveremail")
    private String driveremail;

    @Column(name = "otp")
    @JsonIgnore
    private String otp;

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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    //@JsonIgnore
    public int getRequestid() {
        return requestid;
    }

    public SignUp getUser() {
        return user;
    }

    public void setUser(SignUp user) {
        this.user = user;
    }

    @JsonIgnore
    public String getEmail() {
        return email;
    }

    @JsonProperty
    public void setEmail(String email) {
        this.email = email;
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

    public void setRequestid(int requestid) {
        this.requestid = requestid;
    }

    public Ecommerce getEcommerce() {
        return ecommerce;
    }

    public void setEcommerce(Ecommerce ecommerce) {
        this.ecommerce = ecommerce;
    }

    //@JsonIgnore
    public Integer getProductid() {
        return productid;
    }

    @JsonProperty
    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    @JsonIgnore
    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
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

    public Integer getSourceid() {
        return sourceid;
    }

    public void setSourceid(Integer sourceid) {
        this.sourceid = sourceid;
    }

    public Integer getDestinationid() {
        return destinationid;
    }

    public void setDestinationid(Integer destinationid) {
        this.destinationid = destinationid;
    }
}
