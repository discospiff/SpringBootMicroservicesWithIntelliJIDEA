package com.myplantdiary.enterprise.dao;

import com.myplantdiary.enterprise.dto.Plant;

import java.io.IOException;
import java.util.List;

public interface IPlantDAO {

    List<Plant> fetchPlants(String combinedName) throws IOException;
}
