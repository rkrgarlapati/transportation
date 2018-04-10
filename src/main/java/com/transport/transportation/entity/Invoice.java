package com.transport.transportation.entity;

import javax.persistence.*;

@Entity
@Table(name = "COMPANYINVOICE")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoiceid")
    private int invoiceid;

    @Transient
    TransportRequest request;

    @Column(name = "requestId")
    private int requestId;


    public int getInvoiceid() {
        return invoiceid;
    }

    public void setInvoiceid(int invoiceid) {
        this.invoiceid = invoiceid;
    }

    public TransportRequest getRequest() {
        return request;
    }

    public void setRequest(TransportRequest request) {
        this.request = request;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }
}
