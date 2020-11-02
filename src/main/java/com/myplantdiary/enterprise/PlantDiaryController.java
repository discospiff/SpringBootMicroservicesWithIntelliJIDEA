package com.myplantdiary.enterprise;

import com.myplantdiary.enterprise.dto.LabelValue;
import com.myplantdiary.enterprise.dto.Photo;
import com.myplantdiary.enterprise.dto.Plant;
import com.myplantdiary.enterprise.dto.Specimen;
import com.myplantdiary.enterprise.service.ISpecimenService;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * The controller fro Plant Diary REST endpoints and web UI
 * <p>
 * This class handles the CRUD operations for My Plant Diary specimens, via HTTP actions.
 * </p>
 * <p>
 * This class also serves HTML based web pages, for UI interactions with plant specimens.
 * </p>
 * @author Brandan Jones
 */
@Controller
public class PlantDiaryController {

    @Autowired
    ISpecimenService specimenService;

    Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * Handle the root (/) endpoint and return a start page.
     * @return
     */
    @RequestMapping("/")
    public String index(Model model) {
        Specimen specimen = new Specimen();
        specimen.setDescription("Pawpaw fruit season");
        specimen.setLatitude("39.74");
        specimen.setLongitude("-84.51");
        specimen.setSpecimenId(1003);
        specimen.setPlantId(84);
        model.addAttribute(specimen);
        return "start";
    }

    @GetMapping("/specimen")
    @ResponseBody
    public List<Specimen> fetchAllSpecimens() {
        return specimenService.fetchAll();
    }

    /**
     * Fetch a specimen with the given ID.
     *
     * Given the parameter id, find a specimen that corresponds to this unique ID.
     *
     * Returns one of the following status codes:
     * 200: specimen found
     * 400: specimen not found
     *
     * @param id a unique identifier for this specimen
     */
    @GetMapping("/specimen/{id}/")
    public ResponseEntity fetchSpecimenById(@PathVariable("id") int id) {
        Specimen foundSpecimen = specimenService.fetchById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(foundSpecimen, headers, HttpStatus.OK);
    }

    /**
     * Create a new specimen object, given the data provided.
     *
     * returns one of the following status codes:
     * 201: successfully created a new specimen.
     * 409: unable to create a specimen, because it already exists.
     *
     * @param specimen a JSON representation of a specimen object.
     * @return the newly created specimen object.
     */
    @PostMapping(value="/specimen", consumes="application/json", produces="application/json")
    public ResponseEntity createSpecimen(@RequestBody Specimen specimen) {
        Specimen newSpecimen = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        try {
            newSpecimen = specimenService.save(specimen);
        } catch (Exception e) {

            return new ResponseEntity(headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(newSpecimen, headers, HttpStatus.OK);
    }

    @DeleteMapping("/specimen/{id}/")
    public ResponseEntity deleteSpecimen(@PathVariable("id") int id) {
        log.debug("Entering delete specimen endpoint");
        try {
            specimenService.delete(id);
            log.info("Specimen with ID " + id + " was deleted.");
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Unable to delete specimen with ID: " + id + ", message: " + e.getMessage(), e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value="/plants", consumes="application/json", produces="application/json")
    public ResponseEntity searchPlants(@RequestParam(value="searchTerm", required=false, defaultValue="None")  String searchTerm) {
        try {
            List<Plant> plants = specimenService.fetchPlants(searchTerm);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity(plants, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/plants")
    public String searchPlantsForm(@RequestParam(value="searchTerm", required=false, defaultValue="None")  String searchTerm, Model model) {
        try {
            List<Plant> plants = specimenService.fetchPlants(searchTerm);
            model.addAttribute("plants", plants);
            return "plants";
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }

    }


    /**
     * Handle the sustainabilty endpoint and return a start page.
     * @return
     */
    @RequestMapping("/sustainability")
    public String sustainability() {
        return "sustainability";
    }

    @GetMapping("/plantNamesAutocomplete")
    @ResponseBody
    public List<LabelValue> plantNamesAutocomplete(@RequestParam(value="term", required = false, defaultValue="") String term) {
        List<LabelValue> allPlantNames = new ArrayList<LabelValue>();
        try {
            List<Plant> plants = specimenService.fetchPlants(term);
            for (Plant plant: plants) {
                LabelValue labelValue = new LabelValue();
                labelValue.setLabel(plant.toString());
                labelValue.setValue(plant.getId());
                allPlantNames.add(labelValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<LabelValue>();
        }
        return allPlantNames;
    }

    @PostMapping("/saveSpecimen")
    public ModelAndView saveSpecimen(Specimen specimen, @RequestParam("imageFile") MultipartFile imageFile, Model model) {
        String returnValue = "start";
        ModelAndView modelAndView = new ModelAndView();
        try {
            specimenService.save(specimen);
        } catch (Exception e) {
            e.printStackTrace();
            modelAndView.setViewName("error");
            return modelAndView;
        }

        Photo photo = new Photo();
        try {
            photo.setFileName(imageFile.getOriginalFilename());
            photo.setSpecimen(specimen);
            specimenService.saveImage(imageFile, photo);
            model.addAttribute("specimen", specimen);
            modelAndView.setViewName("success");
        } catch (IOException e) {
            e.printStackTrace();
            modelAndView.setViewName("error");
            return modelAndView;
        }
        modelAndView.addObject("photo", photo);
        modelAndView.addObject("speicmen", specimen);
        return modelAndView;
    }

    @GetMapping("/specimensByPlant/{plantId}/")
    public ModelAndView specimensByPlant(@PathVariable("plantId") int plantId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("specimenDetails");
        List<Specimen> specimens = specimenService.fetchSpecimensByPlantId(plantId);
        modelAndView.addObject("specimens", specimens);
        return  modelAndView;

    }
}
