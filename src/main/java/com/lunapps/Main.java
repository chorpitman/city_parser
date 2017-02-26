package com.lunapps;

import com.lunapps.configuration.AppConfig;
import com.lunapps.model.Model;
import com.lunapps.repository.ModelRepository;
import com.lunapps.sevice.impl.*;
import com.lunapps.utils.Utils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collection;
import java.util.List;

public class Main {
    private final static String PARSE_UKR_DB = "src/main/resources/UA.txt";
    private final static String PARSE_ALTER_NAME_DB = "src/main/resources/alternateNames.txt";
    private final static String DOWNLOAD_DIRECTORY = "src/main/resources/downloads";
    private final static String DOWNLOAD_UKR_DB = "http://download.geonames.org/export/dump/UA.zip";
    private final static String DOWNLOAD_ALTER_NAMES_DB = "http://download.geonames.org/export/dump/alternateNames.zip";
    private final static String ZIP_UKR_DB = "src/main/resources/downloads/UA.zip";
    private final static String ZIP_ALTER_NAMES_DB = "src/main/resources/downloads/alternateNames.zip";
    private final static String UNZIP_DIRECTORY = "src/main/resources/";

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        //GET SPRING CONTEXT
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        //DOWNLOAD FILE
        DownloadFileImpl downloadFile = context.getBean(DownloadFileImpl.class);
        downloadFile.downloadFile(DOWNLOAD_UKR_DB, DOWNLOAD_DIRECTORY);
        downloadFile.downloadFile(DOWNLOAD_ALTER_NAMES_DB, DOWNLOAD_DIRECTORY);

        //UNZIP FILE
        UnzipFIleImpl unzipFile = context.getBean(UnzipFIleImpl.class);
        unzipFile.unzip(ZIP_UKR_DB, UNZIP_DIRECTORY);
        unzipFile.unzip(ZIP_ALTER_NAMES_DB, UNZIP_DIRECTORY);

        //PARSE FILE
        ScannerFileImpl scannerfile = context.getBean(ScannerFileImpl.class);
        Collection<Model> ukrModels = scannerfile.parseDbFiles(PARSE_UKR_DB, PARSE_ALTER_NAME_DB);
        System.out.println(ukrModels.size());
        System.out.println("non cyr -->" + Utils.countNonCyrillicModel(ukrModels));

        //CREATE LIST WITH NON_CYR CITY NAME
        Collection<Model> modelWithNonCyrCityName = Utils.getNonCyrCityNameModels(ukrModels);
        System.out.println("modelWithNonCyrCityName size->" + modelWithNonCyrCityName.size());

        //REMOVE FROM MODEL LIST ENTITY WITH NON_CYR CITY NAME
        Utils.removeNonCyrCityNameModels(ukrModels);
        System.out.println("model size after remove non cyr entity -> " + ukrModels.size());
        System.out.println("non cyr size " + Utils.countNonCyrillicModel(ukrModels));

        //GOOGLE PLACES API
        GooglePlacesSearchImpl placesSearch = context.getBean(GooglePlacesSearchImpl.class);
        Collection<Model> googleNearbySearch = placesSearch.nearbySearch(modelWithNonCyrCityName, "uk");
        System.out.println("googleNearbySearch noncyr size after nearby search ->" + Utils.countNonCyrillicModel(googleNearbySearch));

        //GOOGLE MAPS API
        GoogleMapsSearchImpl googleMaps = context.getBean(GoogleMapsSearchImpl.class);
        Collection<Model> geoDecodedModelList = googleMaps.searchCityNameByCoordinatesUsingGoogleMaps(googleNearbySearch);
        System.out.println("geoDecodedModelList count non cyr " + Utils.countNonCyrillicModel(geoDecodedModelList));

        //REMOVE FROM MODEL LIST ENTITY WITH NON_CYR CITY NAME
        Utils.removeNonCyrCityNameModels(geoDecodedModelList);
        System.out.println("model size after remove non cyr entity -> " + geoDecodedModelList.size());
        System.out.println("non cyr size " + Utils.countNonCyrillicModel(geoDecodedModelList));

        //UNION TWO COLLECTION (MODEL WITH GOOGLE COLLECTION)
        ukrModels.addAll(geoDecodedModelList);
        System.out.println("new model size 22625 + " + geoDecodedModelList.size() + " = " + ukrModels.size());

        //REMOVE DUPLICATES BY COORDINATES
        Utils.removeNonCyrCityNameModels(ukrModels);
        List<Model> sortedUkrModel = Utils.sortModelByRegCityLat(ukrModels);
        Collection<Model> duplicatesCoordinates = Utils.getModelsWithDuplicatesCoordinates(sortedUkrModel);
        sortedUkrModel.removeAll(duplicatesCoordinates);

        //SPRING START
        //save model
        ModelRepository modelRepository = context.getBean(ModelRepository.class);
        modelRepository.save(sortedUkrModel);
        System.out.println(sortedUkrModel.size());

        long finish = System.currentTimeMillis();
        System.out.println("time for save -->" + (finish - start) / 1000 + " sec");
    }
}

