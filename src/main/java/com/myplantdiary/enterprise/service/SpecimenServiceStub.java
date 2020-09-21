package com.myplantdiary.enterprise.service;

import com.myplantdiary.enterprise.dao.ISpecimenDAO;
import com.myplantdiary.enterprise.dto.Specimen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecimenServiceStub implements ISpecimenService {

    @Autowired
    private ISpecimenDAO specimenDAO;

    public SpecimenServiceStub() {

    }

    public SpecimenServiceStub(ISpecimenDAO specimenDAO) {

        this.specimenDAO = specimenDAO;
    }

    @Override
    public Specimen fetchById(int id) {
        Specimen foundSpecimen = specimenDAO.fetch(id);
        return foundSpecimen;
    }

    @Override
    public void delete(int id) throws Exception {
        specimenDAO.delete(id);
    }

    @Override
    public Specimen save(Specimen specimen) throws Exception {
        return specimenDAO.save(specimen);
    }

    @Override
    public List<Specimen> fetchAll() {
        return specimenDAO.fetchAll();
    }
}
