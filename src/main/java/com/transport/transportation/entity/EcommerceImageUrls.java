package com.transport.transportation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ECOMMERCE")
public class EcommerceImageUrls {

    @Id
    @Column(name = "PRODUCTID")
    private Integer productid;

    @Column(name = "PRODUCTNAME")
    private String productname;

    @Column(name = "DISCRIPTION")
    private String discription;

    @Column(name = "PRICE")
    private Float price;

    private String imageurl;


    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }
}
