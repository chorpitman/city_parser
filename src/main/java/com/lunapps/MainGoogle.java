package com.lunapps;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.NearbySearchRequest;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

import java.util.Arrays;

public class MainGoogle {
    private final static String GOOGLE_API_KEY = "AIzaSyBX1GaqM18D8C3bbYjGioWz5noOp7eHSQo";
    private final static double LATITUDE = 48.0575;
    private final static double LONGITUDE = 39.83111;

    public static void main(String[] args) throws Exception {

        //GOOGLE MAPS
        LatLng latLng = new LatLng(LATITUDE, LONGITUDE);
        GeoApiContext apiContext = new GeoApiContext().setApiKey(GOOGLE_API_KEY);

        GeocodingResult[] dnipros = GeocodingApi.reverseGeocode(apiContext, latLng).await();
        System.out.println((dnipros[0].formattedAddress));

//        NearbySearchRequest nearbySearchRequest = new NearbySearchRequest(apiContext);
//        NearbySearchRequest location = nearbySearchRequest.location(latLng);
    }
}
