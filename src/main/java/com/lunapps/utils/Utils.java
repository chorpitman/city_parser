package com.lunapps.utils;

import com.lunapps.model.Model;
import com.lunapps.sevice.impl.ScannerFileImpl;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.*;

public class Utils {
    private static final String NAME_NON_CYR = "non cyrillic";

    public static int countNonCyrillicModel(Collection<Model> models) {
        if (CollectionUtils.isEmpty(models)) throw new IllegalArgumentException("Collection can not be empty or null");

        int count = 0;
        if (CollectionUtils.isNotEmpty(models)) {
            for (Model entity : models) {
                String name = entity.getCityUkrName();
                if (Objects.equals(name, NAME_NON_CYR)) {
                    count++;
                }
            }
        }
        return count;
    }

    public static Collection<Model> getNonCyrCityNameModels(Collection<Model> modelList) {
        if (CollectionUtils.isEmpty(modelList))
            throw new IllegalArgumentException("modelList can not be null or empty");

        ArrayList<Model> nonCyrList = new ArrayList<>();
        for (Model model : modelList) {
            if (model.getCityUkrName().equals("non cyrillic")) {
                nonCyrList.add(model);
            }
        }
        return nonCyrList;
    }

    public static void removeNonCyrCityNameModels(Collection<Model> models) {
        if (CollectionUtils.isEmpty(models)) throw new IllegalArgumentException("models can not be empty or null");
        models.removeIf(model -> model.getCityUkrName().equals(NAME_NON_CYR));
    }

    public static List<Model> getModelsWithDuplicatesCoordinates(List<Model> modelsList) {
        // FIXME: 2/24/17 think about distance between 2 points (km)
        final int DISTANCE = 1;

        if (CollectionUtils.isEmpty(modelsList)) throw new IllegalArgumentException();
        String currentCityUkrName = "";
        String currentRegionUkrName = "";
        double lat = 0;
        double lon = 0;

        ArrayList<Model> duplicatesList = new ArrayList<>();

        for (int i = 0; i < modelsList.size(); i++) {
            Model model = modelsList.get(i);
            if (StringUtils.isBlank(currentCityUkrName) & StringUtils.isBlank(currentRegionUkrName)) {
                currentCityUkrName = model.getCityUkrName();
                currentRegionUkrName = model.getRegionCyrillicName();
                lat = model.getLatitude();
                lon = model.getLongitude();

            } else if (Objects.equals(currentRegionUkrName, model.getRegionCyrillicName()) &
                    Objects.equals(currentCityUkrName, model.getCityUkrName())) {
                if (Utils.getDistanceBetween2Points(lat, lon, model.getLatitude(), model.getLongitude()) <= DISTANCE) {
                    // FIXME: 2/24/17 create constants
                    if (Objects.equals(model.getFeatureCode(), "PPLA") ||
                            Objects.equals(model.getFeatureCode(), "PPLC") ||
                            Objects.equals(model.getFeatureCode(), "ADM1")) {

                        if (Objects.equals(modelsList.get(i - 1).getFeatureCode(), "PPL")) {
                            duplicatesList.add(modelsList.get(i - 1));
                            continue;
                        }
                        continue;

                    } else {
                        // FIXME: 2/24/17 Remove after test
//                        System.out.println("distance <= 1 " + model.getCityIndex() + "coordinates lat-> " + model.getLatitude());
                        duplicatesList.add(model);
                    }
                } else {
                    lat = model.getLatitude();
                    lon = model.getLongitude();
                }
            } else {
                currentCityUkrName = model.getCityUkrName();
                currentRegionUkrName = model.getRegionCyrillicName();
                lat = model.getLatitude();
                lon = model.getLongitude();
            }
        }
        // FIXME: 2/23/17 remove after test
//        duplicatesList.removeAll(list);
        new ScannerFileImpl().print(duplicatesList);
        System.out.println(duplicatesList.size());
        return duplicatesList;
    }

    public static List<Model> sortModelByRegCityLat(Collection<Model> list) {
        List<Model> sortedList = new LinkedList<>(list);
        Collections.sort(sortedList, Comparator.comparing(Model::getRegionCyrillicName)
                .thenComparing(Model::getCityUkrName)
                .thenComparing(Model::getLatitude));
        return sortedList;
    }

    public static Double getDistanceBetween2Points(Double lat1, Double lon1, Double lat2, Double lon2) {
        final int R = 6371; // Radious of the earth

        Double latDistance = toRad(lat2 - lat1);
        Double lonDistance = toRad(lon2 - lon1);

        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        Double distance = R * c;
        // FIXME: 2/23/17 remove after test
        System.out.println("The distance between two lat and long is -> " + distance);
        return distance;
    }

    private static Double toRad(Double value) {
        return value * Math.PI / 180;
    }
}
