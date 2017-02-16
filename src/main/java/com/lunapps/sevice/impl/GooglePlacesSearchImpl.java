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
    public static final int QUERY_COUNT = 10;

    @Override
    public Collection<Model> nearbySearch(Collection<Model> nonCyrList, String language) {
        final String LANGUAGE = language;
        if (CollectionUtils.isEmpty(nonCyrList)) throw new IllegalArgumentException("nonCyrList can bot be null");

        ArrayList<Model> nearbySearchedList = new ArrayList<>(nonCyrList);

        int count = 0;
        for (Model model : nearbySearchedList) {
            if (count == QUERY_COUNT) {
                break;
            }
            final Double latitude = model.getLatitude();
            final Double longitude = model.getLongitude();

            if (latitude != 0 & longitude != 0) {
                try {
                    List<Place> placeList = Places.nearbySearch(Places.Params.create()
                            .latitude(latitude)
                            .longitude(longitude)
                            .radius(RADIUS)
                            .language(LANGUAGE), Places.FIELD_NAME)
                            .getResult();
                    if (placeList.size() >= 1) {
                        String vicinity = placeList.get(0).getVicinity();
                        if (StringUtils.isNotBlank(vicinity)) {
                            model.setCityUkrName(vicinity);
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
