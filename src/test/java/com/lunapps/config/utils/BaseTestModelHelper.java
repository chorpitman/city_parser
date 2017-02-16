package com.lunapps.config.utils;

import com.lunapps.model.Model;

public class BaseTestModelHelper {

    public static Model getModel(int cityIndex, String cityUkrName, String cityInterName, double latitude,
                                 double longitude, String regId, String regCyrName, String regInterName, String featureCode) {
        Model model = new Model();
        model.setCityIndex(cityIndex);
        model.setCityUkrName(cityUkrName);
        model.setInternationalName(cityInterName);
        model.setLatitude(latitude);
        model.setLongitude(longitude);
        model.setRegionId(regId);
        model.setRegionCyrillicName(regCyrName);
        model.setRegionInternationalName(regInterName);
        model.setFeatureCode(regId);

        return model;
    }

    public static Model getModel(String cityUkrName) {
        Model model = new Model();
        model.setCityUkrName(cityUkrName);

        return model;
    }

    public static Model getModel() {
        Model model = new Model();
        return model;
    }
}
