package com.transport.transportation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "SOURCE")
public class Source extends  SourceName{

    @Id
    @Column(name="SOURCEID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer sourceid;


    @JsonProperty
    public Integer getSourceid() {
        return sourceid;
    }

    @JsonIgnore
    public void setSourceid(Integer sourceid) {
        this.sourceid = sourceid;
    }


}
