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
    private int requestid;

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
    @Column(name = "email")
    @JsonProperty
    private String email;

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
    @NotNull
    private String requestStatus;

    @Column(name = "usertype")
    private String userType;

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

    public int getRequestid() {
        return requestid;
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



    @Override
    public String toString() {
        return "TransportRequest{" +
                "requestid=" + requestid +
                ", sour=" + sour +
                ", dest=" + dest +
                ", user=" + user +
                ", sourceId=" + sourceId +
                ", destinationId=" + destinationId +
                ", email='" + email + '\'' +
                ", cost=" + cost +
                ", mobileNo='" + mobileNo + '\'' +
                ", dateTime=" + dateTime +
                ", cashInHand=" + cashInHand +
                ", multipleTrips=" + multipleTrips +
                ", roundTrips=" + roundTrips +
                ", requestStatus='" + requestStatus + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}
