package com.transport.transportation.entity;

import javax.persistence.*;

@Entity
@Table(name = "transitcustomdocs")
public class TransitCustomDocs extends TransitCustomsDocsName {

    @Id
    @Column(name = "DOCID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int docid;

    public int getDocid() {
        return docid;
    }

    public void setDocid(int docid) {
        this.docid = docid;
    }
}
