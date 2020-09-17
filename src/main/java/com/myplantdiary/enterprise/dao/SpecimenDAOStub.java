package com.myplantdiary.enterprise.dao;

import com.myplantdiary.enterprise.dto.Specimen;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SpecimenDAOStub implements ISpecimenDAO {

    List<Specimen> allSpecimens = new ArrayList<Specimen>();

    @Override
    public Specimen save(Specimen specimen) throws Exception {
        allSpecimens.add(specimen);
        return specimen;
    }

    @Override
    public List<Specimen> fetchAll() {
        return allSpecimens;
    }
}
