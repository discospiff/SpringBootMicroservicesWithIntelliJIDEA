package com.myplantdiary.enterprise.dto;

import lombok.Data;
import lombok.ToString;

public @Data
class Photo {

    private int photoId;
    private String photographer;
    private String path;
    private String fileName;
    private String comments;

    @ToString.Exclude
    private Specimen specimen;
}
