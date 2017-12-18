package com.isa.pad.sunglasseswarehouse.service;

import com.isa.pad.sunglasseswarehouse.model.Case;

import java.util.List;
import java.util.Optional;

/**
 * Created by Faust on 12/18/2017.
 */
public interface CaseService {
    Optional<Case> findById(long id);

    Optional<Case> findByModel(String model);

    List<Case> findByAnyField(String q);

    void saveCase(Case c);

    void updateCase(Case c);

    void deleteCaseById(long id);

    List<Case> findAllCases();

    boolean caseExists(Case c);

    void deleteAllCases();

}
