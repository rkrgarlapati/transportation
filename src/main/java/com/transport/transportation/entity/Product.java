package com.transport.transportation.entity;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT")
public class Product extends ProductName{

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
