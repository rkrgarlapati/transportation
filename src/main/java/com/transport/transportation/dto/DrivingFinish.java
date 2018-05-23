package com.transport.transportation.dto;

import javax.validation.constraints.NotNull;

public class DrivingFinish {

    @NotNull
    private String driveremail;

    @NotNull
    private Integer requestId;

    @NotNull
    private String reqStatus;

    public String getDriveremail() {
        return driveremail;
    }

    public void setDriveremail(String driveremail) {
        this.driveremail = driveremail;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public String getReqStatus() {
        return reqStatus;
    }

    public void setReqStatus(String reqStatus) {
        this.reqStatus = reqStatus;
    }

}
