package ar.edu.utn.frc.tup.lc.iii.services;

import ar.edu.utn.frc.tup.lc.iii.models.DummyModel;

import java.util.List;

public interface DummyService {

    DummyModel getDummy(Long id);
    List<DummyModel> getDummyList();
    DummyModel createDummy(DummyModel dummyModel);
    DummyModel updateDummy(DummyModel dummyModel);
    Void deleteDummy(DummyModel dummyModel);
}
