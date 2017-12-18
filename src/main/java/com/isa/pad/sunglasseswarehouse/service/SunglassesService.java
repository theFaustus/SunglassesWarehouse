package com.isa.pad.sunglasseswarehouse.service;

import com.isa.pad.sunglasseswarehouse.model.Sunglasses;

import java.util.List;
import java.util.Optional;

/**
 * Created by Faust on 12/18/2017.
 */
public interface SunglassesService {
    Optional<Sunglasses> findById(long id);

    Optional<Sunglasses> findByModel(String model);

    void saveSunglasses(Sunglasses s);

    void updateSunglasses(Sunglasses s);

    void deleteSunglassesById(long id);

    List<Sunglasses> findAllSunglasses();

    List<Sunglasses> findByAnyField(String q);

    boolean sunglassesExists(Sunglasses s);

    void deleteAllSunglasses();
}
