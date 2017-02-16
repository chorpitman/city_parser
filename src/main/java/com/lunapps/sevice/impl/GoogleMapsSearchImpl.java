package com.lunapps.sevice.impl;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.AddressComponent;
import com.google.maps.model.AddressType;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.lunapps.model.Model;
import com.lunapps.sevice.GoogleMapsSearch;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Collection;

public class GoogleMapsSearchImpl implements GoogleMapsSearch {
    private final static String GOOGLE_API_KEY = "AIzaSyDLBD0Lu4EU3JKChRLEli4K_ZQ7ZhLKMEg";
    private final static String QUERY_RETURN_LANGUAGE = "uk";

    private final static int QUERY_COUNT = 1547;

    @Override
    public Collection<Model> searchCityCyrNameByCoordinatesUsingGoogle(Collection<Model> nonCyrModelList) throws Exception {
        if (CollectionUtils.isEmpty(nonCyrModelList))
            throw new IllegalArgumentException("nonCyrModelList or googleApi cannot be null or empty");

        GeoApiContext apiContext = new GeoApiContext().setApiKey(GOOGLE_API_KEY);
        ArrayList<Model> decodedList = new ArrayList<>(nonCyrModelList);

        int count = 0;
        for (Model model : decodedList) {
            if (count == QUERY_COUNT) {
                break;
            } else {
                LatLng latLng = new LatLng(model.getLatitude(), model.getLongitude());
                GeocodingResult[] geocodingResults = GeocodingApi.reverseGeocode(apiContext, latLng)
                        .language(QUERY_RETURN_LANGUAGE)
                        .resultType(AddressType.POLITICAL).await();
                AddressComponent[] addressComponents = geocodingResults[0].addressComponents;
                if (addressComponents != null & addressComponents.length != 0) {
                    String longName = addressComponents[0].longName;
                    if (StringUtils.isNotBlank(longName)) {
                        model.setCityUkrName(longName);
                        count++;
                    }
                }
            }
        }
        return decodedList;
    }
}
