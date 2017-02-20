package com.lunapps;

import com.lunapps.sevice.impl.ScannerFileImpl;
import net.sf.sprockets.google.Place;
import net.sf.sprockets.google.Places;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.List;

public class MainGooglePlaces {
    private final static double latitude = 46.4063323;
    private final static double longitude = 29.68057199999999;
    private final static int RADIUS = 500;
    private final static String LANGUAGE = "uk";

    public static void main(String[] args) throws IOException {

        List<Place> placeList = Places.nearbySearch(Places.Params
                .create()
                .latitude(latitude)
                .longitude(longitude)
                .radius(RADIUS)
                .language(LANGUAGE), Places.FIELD_VICINITY)
                .getResult();
        if (placeList.size() != 0) {
            String vicinity = placeList.get(0).getVicinity();
            if ((StringUtils.isNotBlank(vicinity)) & (ScannerFileImpl.languageCheck(vicinity))) {
                System.out.println(vicinity);
            }
        }
    }
}
