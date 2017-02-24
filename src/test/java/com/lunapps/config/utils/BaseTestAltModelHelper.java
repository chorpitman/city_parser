package com.lunapps.config.utils;

import com.lunapps.model.AlternativeModel;

import java.util.ArrayList;
import java.util.List;

public class BaseTestAltModelHelper {

    public static AlternativeModel getAlternativeModel(long geonameId, String isoLang, String cyrillicName) {
        AlternativeModel alternativeModel = new AlternativeModel();
        alternativeModel.setGeoNameId(geonameId);
        alternativeModel.setIsoLang(isoLang);
        alternativeModel.setCyrillicName(cyrillicName);
        return alternativeModel;
    }

    public static List<AlternativeModel> makeDuplicate(AlternativeModel model, int countOfDuplicate) {
        List<AlternativeModel> duplicateList = new ArrayList<>();
        for (int i = 0; i < countOfDuplicate; i++) {
            AlternativeModel alternativeModel = new AlternativeModel();
            alternativeModel.setId(model.getId());
            alternativeModel.setGeoNameId(model.getGeoNameId());
            alternativeModel.setIsoLang(model.getIsoLang());
            alternativeModel.setCyrillicName(model.getCyrillicName());
            duplicateList.add(alternativeModel);
        }
        return duplicateList;
    }
}
