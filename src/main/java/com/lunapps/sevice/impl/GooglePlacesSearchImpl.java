package com.lunapps.sevice.impl;

import com.lunapps.model.Model;
import com.lunapps.sevice.GooglePlacesSearch;
import net.sf.sprockets.google.Place;
import net.sf.sprockets.google.Places;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GooglePlacesSearchImpl implements GooglePlacesSearch {
    private final static int RADIUS = 500;

    @Override
    public Collection<Model> nearbySearch(Collection<Model> nonCyrList, String language) {
        final String LANGUAGE = language;
        if (CollectionUtils.isEmpty(nonCyrList)) throw new IllegalArgumentException("nonCyrList can bot be null");

        final int QUERY_COUNT = nonCyrList.size();
        ArrayList<Model> nearbySearchedList = new ArrayList<>(nonCyrList);

        int count = 0;
        int countQuery = 0;
        for (Model model : nearbySearchedList) {
            if (countQuery == QUERY_COUNT) break;

            final Double latitude = model.getLatitude();
            final Double longitude = model.getLongitude();

            if ((latitude != 0) & (longitude != 0)) {
                try {
                    List<Place> placeList = Places.nearbySearch(Places.Params
                            .create()
                            .latitude(latitude)
                            .longitude(longitude)
                            .radius(RADIUS)
                            .language(LANGUAGE), Places.FIELD_VICINITY)
                            .getResult();
                    countQuery++;
                    if (placeList.size() != 0) {
                        String vicinity = placeList.get(0).getVicinity();
                        if (StringUtils.isNotBlank(vicinity) & ScannerFileImpl.languageCheck(vicinity)) {
                            double lat = placeList.get(0).getLatitude();
                            double lon = placeList.get(0).getLongitude();
                            model.setCityUkrName(vicinity);
                            model.setLatitude(lat);
                            model.setLongitude(lon);
                            count++;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return nearbySearchedList;
    }
}
