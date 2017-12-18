package com.isa.pad.sunglasseswarehouse.service.impl;

import com.isa.pad.sunglasseswarehouse.model.Glass;
import com.isa.pad.sunglasseswarehouse.model.Sunglasses;
import com.isa.pad.sunglasseswarehouse.service.SunglassesService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Faust on 12/18/2017.
 */
@Service
public class SunglassesServiceImpl implements SunglassesService{
    private static final AtomicLong index = new AtomicLong();
    private static List<Sunglasses> listOfSunglasses;

    @Override
    public Optional<Sunglasses> findById(long id) {
        return listOfSunglasses.stream().filter(c -> c.getId() == id).findAny();
    }

    @Override
    public Optional<Sunglasses> findByModel(String model) {
        return listOfSunglasses.stream().filter(c -> c.getModel().equals(model)).findAny();
    }

    @Override
    public void saveSunglasses(Sunglasses s) {
        s.setId(index.incrementAndGet());
        listOfSunglasses.add(s);
    }

    @Override
    public void updateSunglasses(Sunglasses s) {
        listOfSunglasses.set(listOfSunglasses.indexOf(s), s);
    }

    @Override
    public void deleteSunglassesById(long id) {
        listOfSunglasses.removeIf(c -> c.getId() == id);
    }

    @Override
    public List<Sunglasses> findAllSunglasses() {
        return listOfSunglasses;
    }

    @Override
    public boolean sunglassesExists(Sunglasses s) {
        return listOfSunglasses.stream().anyMatch(ss -> ss.equals(s));
    }

    @Override
    public void deleteAllSunglasses() {
        listOfSunglasses.clear();
    }

    static {
        listOfSunglasses = populateWithCases();
    }

    private static List<Sunglasses> populateWithCases() {
        List<Sunglasses> cases = new ArrayList<>();
        cases.add(Sunglasses.newSunglasses().id(index.incrementAndGet()).brand("Calvin Klein").frameColor("Black").frameMaterial("Plastic-Metal").model("CK789S").glass(new Glass("Glass", "Blue")).build());
        cases.add(Sunglasses.newSunglasses().id(index.incrementAndGet()).brand("Calvin Klein").frameColor("Blue").frameMaterial("Plastic").model("CK659S").glass(new Glass("Plastic", "Brown")).build());
        cases.add(Sunglasses.newSunglasses().id(index.incrementAndGet()).brand("Lacoste").frameColor("Green").frameMaterial("Metal").model("LS178S").glass(new Glass("Glass", "Black")).build());
        cases.add(Sunglasses.newSunglasses().id(index.incrementAndGet()).brand("Lacoste").frameColor("Green").frameMaterial("Plastic").model("LS576S").glass(new Glass("Glass", "Chameleon")).build());
        cases.add(Sunglasses.newSunglasses().id(index.incrementAndGet()).brand("Tommy Hilfiger").frameColor("Blue").frameMaterial("Metal").model("TH175S").glass(new Glass("Plastic", "Yellow")).build());
        cases.add(Sunglasses.newSunglasses().id(index.incrementAndGet()).brand("Tommy Hilfiger").frameColor("Orange").frameMaterial("Wood").model("TH224S").glass(new Glass("Glass", "Rose")).build());
        return cases;
    }
}
