package com.myplantdiary.enterprise.dao;

import com.myplantdiary.enterprise.dto.Specimen;

import java.util.List;

public interface ISpecimenDAO {
    Specimen save(Specimen specimen) throws Exception;

    List<Specimen> fetchAll();
}
