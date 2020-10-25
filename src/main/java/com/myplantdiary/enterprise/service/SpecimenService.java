package com.myplantdiary.enterprise.service;

import com.myplantdiary.enterprise.dao.IPhotoDAO;
import com.myplantdiary.enterprise.dao.IPlantDAO;
import com.myplantdiary.enterprise.dao.ISpecimenDAO;
import com.myplantdiary.enterprise.dto.Photo;
import com.myplantdiary.enterprise.dto.Plant;
import com.myplantdiary.enterprise.dto.Specimen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class SpecimenService implements ISpecimenService {

    @Autowired
    private ISpecimenDAO specimenDAO;

    @Autowired
    private IPlantDAO plantDAO;

    @Autowired
    private IPhotoDAO photoDAO;

    public SpecimenService() {

    }

    public SpecimenService(ISpecimenDAO specimenDAO) {

        this.specimenDAO = specimenDAO;
    }

    @Override
    @Cacheable(value="specimen", key="#id")
    public Specimen fetchById(int id) {
        Specimen foundSpecimen = specimenDAO.fetch(id);
        return foundSpecimen;
    }

    @Override
    @CacheEvict(value="specimen", key="#id")
    public void delete(int id) throws Exception {
        specimenDAO.delete(id);
    }

    @Override
    public Specimen save(Specimen specimen) throws Exception {
        return specimenDAO.save(specimen);
    }

    @Override
    @Cacheable("specimens")
    public List<Specimen> fetchAll() {
        return specimenDAO.fetchAll();
    }

    @Override
    @Cacheable("plants")
    public List<Plant> fetchPlants(String combinedName) throws IOException {
        return plantDAO.fetchPlants(combinedName);
    }

    @Override
    public void saveImage(MultipartFile imageFile, Photo photo) throws IOException {
        photoDAO.save(photo);
        photoDAO.saveImage(imageFile, photo);

    }

    @Override
    public List<Specimen> fetchSpecimensByPlantId(int plantId) {
        return specimenDAO.fetchSpecimensByPlantId(plantId);
    }
}
