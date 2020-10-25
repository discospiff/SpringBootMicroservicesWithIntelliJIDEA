package com.myplantdiary.enterprise.dto;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="photos")
public @Data
class Photo {

    @Id
    @GeneratedValue
    private int photoId;
    private String photographer;
    private String path;
    private String fileName;
    private String comments;

    @ManyToOne
    @JoinColumn(name="specimen_id")
    private Specimen specimen;
}
