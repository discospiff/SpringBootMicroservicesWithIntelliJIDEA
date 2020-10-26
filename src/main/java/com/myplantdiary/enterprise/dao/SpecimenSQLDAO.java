package com.myplantdiary.enterprise.dao;

import com.myplantdiary.enterprise.dto.Specimen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Profile({"dev", "default"})
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
        List<Specimen> allSpecimens = new ArrayList<>();
        Iterable<Specimen> specimens = specimenRepository.findAll();
        for (Specimen specimen : specimens) {
            allSpecimens.add(specimen);
        }
        return allSpecimens;
    }

    @Override
    public Specimen fetch(int id) {
        return  specimenRepository.findById(id).get();
    }

    @Override
    public void delete(int id) {
        specimenRepository.deleteById(id);
    }

    @Override
    public List<Specimen> fetchSpecimensByPlantId(int plantId) {
        return specimenRepository.findByPlantId(plantId);
    }
}
