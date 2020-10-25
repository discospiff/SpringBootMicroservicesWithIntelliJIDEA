package com.myplantdiary.enterprise.service;

import com.myplantdiary.enterprise.dto.Photo;
import com.myplantdiary.enterprise.dto.Plant;
import com.myplantdiary.enterprise.dto.Specimen;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    List<Plant> fetchPlants(String combinedName) throws IOException;

    void saveImage(MultipartFile imageFile, Photo photo) throws IOException;

    List<Specimen> fetchSpecimensByPlantId(int plantId);
}
