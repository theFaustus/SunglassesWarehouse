package com.isa.pad.sunglasseswarehouse.service.impl;

import com.isa.pad.sunglasseswarehouse.model.Case;
import com.isa.pad.sunglasseswarehouse.service.CaseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Faust on 12/18/2017.
 */
@Service
public class CaseServiceImpl implements CaseService {
    private static final AtomicLong index = new AtomicLong();
    private static List<Case> listOfCases;

    @Override
    public Optional<Case> findById(long id) {
        return listOfCases.stream().filter(c -> c.getId() == id).findAny();
    }

    @Override
    public Optional<Case> findByModel(String model) {
        return listOfCases.stream().filter(c -> c.getModel().equals(model)).findAny();
    }

    @Override
    public void saveCase(Case c) {
        c.setId(index.incrementAndGet());
        listOfCases.add(c);
    }

    @Override
    public void updateCase(Case c) {
        listOfCases.set(listOfCases.indexOf(c), c);
    }

    @Override
    public void deleteCaseById(long id) {
        listOfCases.removeIf(c -> c.getId() == id);
    }

    @Override
    public List<Case> findAllCases() {
        return listOfCases;
    }

    @Override
    public boolean caseExists(Case c) {
        return listOfCases.stream().anyMatch(cs -> cs.equals(c));
    }

    @Override
    public void deleteAllCases() {
        listOfCases.clear();
    }

    @Override
    public List<Case> findByAnyField(String q) {
        List<Case> result = new ArrayList<>();
        for(Case c : listOfCases){
            if(c.toString().contains(q))
                result.add(c);
        }
        return result;
    }

    static {
        listOfCases = populateWithCases();
    }

    private static List<Case> populateWithCases() {
        List<Case> cases = new ArrayList<>();
        cases.add(Case.newCase().id(index.incrementAndGet()).brand("Calvin Klein").color("Green").material("Leather").model("CK789").build());
        cases.add(Case.newCase().id(index.incrementAndGet()).brand("Calvin Klein").color("Red").material("Plastic").model("CK659").build());
        cases.add(Case.newCase().id(index.incrementAndGet()).brand("Lacoste").color("Green").material("Leather").model("LS178").build());
        cases.add(Case.newCase().id(index.incrementAndGet()).brand("Lacoste").color("Blue").material("Leather").model("LS576").build());
        cases.add(Case.newCase().id(index.incrementAndGet()).brand("Tommy Hilfiger").color("Red").material("Metal").model("TH175").build());
        cases.add(Case.newCase().id(index.incrementAndGet()).brand("Tommy Hilfiger").color("White").material("Leather").model("TH224").build());
        return cases;
    }
}
