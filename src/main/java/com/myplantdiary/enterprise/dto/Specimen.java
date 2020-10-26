package com.myplantdiary.enterprise.dto;

import lombok.Data;

import javax.persistence.*;

@Entity
public @Data
class Specimen {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int specimenId;
    private int plantId;
    private String latitude;
    private String longitude;
    private String description;

}
