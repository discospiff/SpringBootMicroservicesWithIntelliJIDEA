package com.myplantdiary.enterprise.service;

import com.myplantdiary.enterprise.dto.Specimen;

import java.util.List;

public interface ISpecimenService {
    /**
     * Fetch a specimen with a given ID.
     * @param id a unique identifier for a specimen.
     * @return the matching specimen, or null if no matches found.
     */
    Specimen fetchById(int id);

    void delete(int id) throws Exception;

    Specimen save(Specimen specimen) throws Exception;

    List<Specimen> fetchAll();
}
