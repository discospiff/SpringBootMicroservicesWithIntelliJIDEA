package com.myplantdiary.enterprise;

import com.myplantdiary.enterprise.dto.Specimen;
import com.myplantdiary.enterprise.service.ISpecimenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        specimen.setSpecimenId("1003");
        specimen.setPlantId(84);
        model.addAttribute(specimen);
        return "start";
    }

    @RequestMapping("/saveSpecimen")
    public String saveSpecimen(Specimen specimen) {
        try {
            specimenService.save(specimen);
        } catch (Exception e) {
            e.printStackTrace();
            return "start";
        }
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
    public ResponseEntity fetchSpecimenById(@PathVariable("id") String id) {
        Specimen foundSpecimen = specimenService.fetchById(Integer.parseInt(id));
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
    @ResponseBody
    public Specimen createSpecimen(@RequestBody Specimen specimen) {
        Specimen newSpecimen = null;
        try {
            newSpecimen = specimenService.save(specimen);
        } catch (Exception e) {
            // TODO add logging
        }
        return newSpecimen;
    }

    @DeleteMapping("/specimen/{id}/")
    public ResponseEntity deleteSpecimen(@PathVariable("id") String id) {
        try {
            specimenService.delete(Integer.parseInt(id));
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/plants")
    public ResponseEntity searchPlants(@RequestParam(value="searchTerm", required=false, defaultValue="None")  String searchTerm) {
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Handle the sustainabilty endpoint and return a start page.
     * @return
     */
    @RequestMapping("/sustainability")
    public String sustainability() {
        return "sustainability";
    }


}
