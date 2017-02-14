package com.lunapps;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

public class MainGoogle {
    private final static String GOOGLE_KEY = "AIzaSyBX1GaqM18D8C3bbYjGioWz5noOp7eHSQo";
    public static void main(String[] args) throws Exception {

        //GOOGLE MAPS
        LatLng latLng = new LatLng(48.75, 32.5);
        GeoApiContext apiContext = new GeoApiContext().setApiKey(GOOGLE_KEY);

        GeocodingResult[] dnipros = GeocodingApi.reverseGeocode(apiContext, latLng).await();
        System.out.println(dnipros[0].formattedAddress);

    }
}
