package com.lunapps.sevice;

import com.lunapps.model.Model;

import java.util.Collection;

public interface GoogleMapsSearch {

    Collection<Model> searchCityCyrNameByCoordinatesUsingGoogle(Collection<Model> nonCyrModelList) throws Exception;
}
