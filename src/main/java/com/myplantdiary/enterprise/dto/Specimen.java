package com.myplantdiary.enterprise.dto;

import lombok.Data;

import java.util.List;

public @Data
class Specimen {

    private int specimenId;
    private int plantId;
    private String latitude;
    private String longitude;
    private String description;

    private List<Photo> photos;

}
