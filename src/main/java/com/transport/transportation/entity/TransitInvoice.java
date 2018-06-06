package com.transport.transportation.entity;

import javax.persistence.*;

@Entity
@Table(name = "TRANSITCOMPANYINVOICE")
public class TransitInvoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INVOICEID")
    private int invoiceid;

    @Column(name = "REQUESTID")
    private int requestid;


    public int getInvoiceid() {
        return invoiceid;
    }

    public void setInvoiceid(int invoiceid) {
        this.invoiceid = invoiceid;
    }

    public int getRequestid() {
        return requestid;
    }

    public void setRequestid(int requestid) {
        this.requestid = requestid;
    }
}
