package com.isa.pad.sunglasseswarehouse.controller;

import com.isa.pad.sunglasseswarehouse.model.Case;
import com.isa.pad.sunglasseswarehouse.service.CaseService;
import com.isa.pad.sunglasseswarehouse.util.ErrorMessage;
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
@RequestMapping("/cases")
public class CaseControllerREST {
    public static final Logger logger = LoggerFactory.getLogger(CaseControllerREST.class);

    @Autowired
    CaseService caseService;

    @RequestMapping(value = "/list/", method = RequestMethod.GET)
    public ResponseEntity<List<Case>> listAllCases() {
        List<Case> allCases = caseService.findAllCases();
        if (allCases.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(allCases, HttpStatus.OK);
    }

    @RequestMapping(value = "/case/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getCase(@PathVariable("id") long id) {
        Optional<Case> foundCase = caseService.findById(id);
        if (foundCase.isPresent())
            return new ResponseEntity<>(foundCase.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>("No such case with id = " + id, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/case/", method = RequestMethod.GET, params = "model")
    public ResponseEntity<?> getCaseByModel(@RequestParam("model") String model) {
        Optional<Case> foundCase = caseService.findByModel(model);
        if (foundCase.isPresent())
            return new ResponseEntity<>(foundCase.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>("No such case with id = " + model, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/case/", method = RequestMethod.GET, params = "q")
    public ResponseEntity<?> getCaseByAnyField(@RequestParam("q") String q) {
        List<Case> allCases = caseService.findByAnyField(q);
        if (allCases.isEmpty())
            return new ResponseEntity<>("No such cases with requested query = " + q, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(allCases, HttpStatus.OK);
    }

    @RequestMapping(value = "/case/", method = RequestMethod.GET, params = "model_starts_with")
    public ResponseEntity<?> getCaseByModelStartsWith(@RequestParam("model_starts_with") String text) {
        List<Case> allCases = caseService.findByModelStartsWith(text);
        if (allCases.isEmpty())
            return new ResponseEntity<>("No such cases which start with = " + text, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(allCases, HttpStatus.OK);
    }

    @RequestMapping(value = "/case/", method = RequestMethod.POST)
    public ResponseEntity<?> createCase(@RequestBody Case c, UriComponentsBuilder uriComponentsBuilder) {
        if (caseService.caseExists(c))
            return new ResponseEntity<>("Unable to create case. Already exists.", HttpStatus.CONFLICT);
        caseService.saveCase(c);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uriComponentsBuilder.path("/cases/case/{id}").buildAndExpand(c.getId()).toUri());
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/case/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCase(@PathVariable("id") long id, @RequestBody Case c) {
        Optional<Case> foundCase = caseService.findById(id);
        if (foundCase.isPresent()) {
            Case tempCase = foundCase.get();
            tempCase.setBrand(c.getBrand());
            tempCase.setColor(c.getColor());
            tempCase.setMaterial(c.getMaterial());
            tempCase.setModel(c.getModel());
            caseService.updateCase(tempCase);
            return new ResponseEntity<>(tempCase, HttpStatus.OK);
        } else
            return new ResponseEntity<>("Unable to update. No such case with id = " + id, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/case/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCase(@PathVariable("id") long id) {
        Optional<Case> foundCase = caseService.findById(id);
        if (foundCase.isPresent()) {
            caseService.deleteCaseById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else
            return new ResponseEntity<>("Unable to delete. No such case with id = " + id, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/case/", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAllCases() {
        caseService.deleteAllCases();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
