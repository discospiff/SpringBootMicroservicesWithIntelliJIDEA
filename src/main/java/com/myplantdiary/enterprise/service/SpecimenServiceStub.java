package com.myplantdiary.enterprise.service;

import com.myplantdiary.enterprise.dao.ISpecimenDAO;
import com.myplantdiary.enterprise.dto.Specimen;
import org.springframework.stereotype.Component;

@Component
public class SpecimenServiceStub implements ISpecimenService {

    private ISpecimenDAO specimenDAO;

    public SpecimenServiceStub() {

    }

    public SpecimenServiceStub(ISpecimenDAO specimenDAO) {

        this.specimenDAO = specimenDAO;
    }

    @Override
    public Specimen fetchById(int id) {
        Specimen specimen = new Specimen();
        specimen.setDescription("Eastern Redbud");
        specimen.setSpecimenId("83");
        return specimen;
    }

    @Override
    public Specimen save(Specimen specimen) throws Exception {
        return specimenDAO.save(specimen);
    }
}
