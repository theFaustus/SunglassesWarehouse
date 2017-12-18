package com.isa.pad.sunglasseswarehouse.controller;

import com.isa.pad.sunglasseswarehouse.model.Case;
import com.isa.pad.sunglasseswarehouse.model.Sunglasses;
import com.isa.pad.sunglasseswarehouse.service.SunglassesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

/**
 * Created by Faust on 12/18/2017.
 */
@RestController
@RequestMapping("/sunglasses")
public class SunglassesControllerREST {
    public static final Logger logger = LoggerFactory.getLogger(SunglassesControllerREST.class);

    @Autowired
    SunglassesService sunglassesService;

    @RequestMapping(value = "/list/", method = RequestMethod.GET)
    public ResponseEntity<List<Sunglasses>> listAllCases() {
        List<Sunglasses> allSunglasses = sunglassesService.findAllSunglasses();
        if (allSunglasses.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(allSunglasses, HttpStatus.OK);
    }

    @RequestMapping(value = "/sg/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getSunglasses(@PathVariable("id") long id) {
        Optional<Sunglasses> foundSunglasses = sunglassesService.findById(id);
        if (foundSunglasses.isPresent())
            return new ResponseEntity<>(foundSunglasses.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>("No such sunglasses with id = " + id, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/sg/", method = RequestMethod.GET, params = "q")
    public ResponseEntity<?> getSunglassesByAnyField(@RequestParam("q") String q) {
        List<Sunglasses> sunglasses = sunglassesService.findByAnyField(q);
        if (sunglasses.isEmpty())
            return new ResponseEntity<>("No such sunglasses with requested query = " + q, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(sunglasses, HttpStatus.OK);
    }

    @RequestMapping(value = "/sg/", method = RequestMethod.GET, params = "model_starts_with")
    public ResponseEntity<?> getSunglassesByModelStartsWith(@RequestParam("model_starts_with") String text) {
        List<Sunglasses> allSunglasses = sunglassesService.findByModelStartsWith(text);
        if (allSunglasses.isEmpty())
            return new ResponseEntity<>("No such sunglasses which start with = " + text, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(allSunglasses, HttpStatus.OK);
    }

    @RequestMapping(value = "/sg/", method = RequestMethod.GET, params = {"start", "end"})
    public ResponseEntity<?> getAllSunglassesByLimit(@RequestParam("start") int start, @RequestParam("end") int end) {
        List<Sunglasses> allSunglasses = sunglassesService.findAllByLimit(start, end);
        if (allSunglasses.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(allSunglasses, HttpStatus.OK);
    }

    @RequestMapping(value = "/sg/", method = RequestMethod.GET, params = "model")
    public ResponseEntity<?> getCase(@RequestParam("model") String model) {
        Optional<Sunglasses> foundCase = sunglassesService.findByModel(model);
        if (foundCase.isPresent())
            return new ResponseEntity<>(foundCase.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>("No such sunglasses with model = " + model, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/sg/", method = RequestMethod.POST)
    public ResponseEntity<?> createCase(@RequestBody Sunglasses s, UriComponentsBuilder uriComponentsBuilder) {
        if (sunglassesService.sunglassesExists(s))
            return new ResponseEntity<>("Unable to create sunglasses. Already exists.", HttpStatus.CONFLICT);
        sunglassesService.saveSunglasses(s);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uriComponentsBuilder.path("/sunglasses/sg/{id}").buildAndExpand(s.getId()).toUri());
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/sg/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCase(@PathVariable("id") long id, @RequestBody Sunglasses s) {
        Optional<Sunglasses> foundCase = sunglassesService.findById(id);
        if (foundCase.isPresent()) {
            Sunglasses tempSunglasses = foundCase.get();
            tempSunglasses.setBrand(s.getBrand());
            tempSunglasses.setFrameColor(s.getFrameColor());
            tempSunglasses.setFrameMaterial(s.getFrameMaterial());
            tempSunglasses.setGlass(s.getGlass());
            tempSunglasses.setModel(s.getModel());
            sunglassesService.updateSunglasses(tempSunglasses);
            return new ResponseEntity<>(tempSunglasses, HttpStatus.OK);
        } else
            return new ResponseEntity<>("Unable to update. No such sunglasses with id = " + id, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/sg/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCase(@PathVariable("id") long id) {
        Optional<Sunglasses> foundCase = sunglassesService.findById(id);
        if (foundCase.isPresent()) {
            sunglassesService.deleteSunglassesById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else
            return new ResponseEntity<>("Unable to delete. No such sunglasses with id = " + id, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/sg/", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAllCases() {
        sunglassesService.deleteAllSunglasses();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
