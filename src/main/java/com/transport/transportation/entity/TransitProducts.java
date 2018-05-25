package com.transport.transportation.entity;

import javax.persistence.*;

@Entity
@Table(name = "TRANSITPRODUCTS")
public class TransitProducts extends TransitProductName {

    @Id
    @Column(name = "PRODUCTID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productid;

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }
}
