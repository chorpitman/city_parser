package com.lunapps;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.AddressComponent;
import com.google.maps.model.AddressType;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

public class MainGoogle {
    private final static String TABULATION = "\\t";
    private final static String COMMA = ",";

    private final static String GOOGLE_API_KEY = "AIzaSyDLBD0Lu4EU3JKChRLEli4K_ZQ7ZhLKMEg";
    private final static double LATITUDE = 48.5603;
    private final static double LONGITUDE = 29.35585;

    public static void main(String[] args) throws Exception {

        //GOOGLE MAPS
        LatLng latLng = new LatLng(LATITUDE, LONGITUDE);
        GeoApiContext apiContext = new GeoApiContext().setApiKey(GOOGLE_API_KEY);

        GeocodingResult[] dnipros = GeocodingApi.reverseGeocode(apiContext, latLng).language("uk").resultType(AddressType.POLITICAL).await();
        AddressComponent[] addressComponents = dnipros[0].addressComponents;
        String longName = addressComponents[0].longName;
        System.out.println(longName);

//        String[] split = dnipros[0].formattedAddress.split(COMMA);
//        String s = split[0];
//        System.out.println(s);

    }
}
