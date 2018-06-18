package com.transport.transportation.entity;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public class EcommerceName {

    @Lob
    @Column(name="IMAGE")
    private byte[] image;

    @NotNull
    @Column(name = "PRODUCTNAME")
    private String productname;

    @NotNull
    @Column(name = "DISCRIPTION")
    private String discription;

    @NotNull
    @Column(name = "PRICE")
    private Float price;

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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
