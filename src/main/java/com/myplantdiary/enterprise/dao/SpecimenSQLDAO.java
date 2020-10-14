package com.myplantdiary.enterprise.dao;

import com.myplantdiary.enterprise.dto.Specimen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("specimenDAO")
public class SpecimenSQLDAO implements ISpecimenDAO {

    @Autowired
    SpecimenRepository specimenRepository;

    @Override
    public Specimen save(Specimen specimen) throws Exception {
        Specimen createdSpecimen = specimenRepository.save(specimen);
        return createdSpecimen;

    }

    @Override
    public List<Specimen> fetchAll() {
        return null;
    }

    @Override
    public Specimen fetch(int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
