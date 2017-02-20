package com.lunapps;

import com.lunapps.configuration.AppConfig;
import com.lunapps.model.Model;
import com.lunapps.repository.ModelRepository;
import com.lunapps.sevice.impl.GoogleMapsSearchImpl;
import com.lunapps.sevice.impl.GooglePlacesSearchImpl;
import com.lunapps.sevice.impl.ScannerFileImpl;
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

        //DOWNLOAD FILE
//        DownloadFile downloadFile = new DownloadFileImpl();
//        downloadFile.downloadFile(DOWNLOAD_UKR_DB, DOWNLOAD_DIRECTORY);
//        downloadFile.downloadFile(DOWNLOAD_ALTER_NAMES_DB, DOWNLOAD_DIRECTORY);

        //UNZIP FILE
//        UnzipFIle unzipFIle = new UnzipFIleImpl();
//        unzipFIle.unzip(ZIP_UKR_DB, UNZIP_DIRECTORY);
//        unzipFIle.unzip(ZIP_ALTER_NAMES_DB, UNZIP_DIRECTORY);

        //PARSE FILE
        ScannerFileImpl scannerfile = new ScannerFileImpl();
        Collection<Model> models = scannerfile.parseDbFiles(PARSE_UKR_DB, PARSE_ALTER_NAME_DB);
        System.out.println(models.size());
        System.out.println("non cyr -->" + Utils.countNonCyrillic(models));

        //CREATE LIST WITH NON_CYR CITY NAME
        Collection<Model> modelWithNonCyrCityName = Utils.returnListModelWithNonCyrCityName(models);
        System.out.println("modelWithNonCyrCityName size->" + modelWithNonCyrCityName.size());

        //REMOVE FROM MODEL LIST ENTITY WITH NON_CYR CITY NAME
        Utils.removeEntityWithNonCyrCityName(models);
        System.out.println("model size after remove non cyr entity -> " + models.size());
        System.out.println("non cyr size " + Utils.countNonCyrillic(models));

        //GOOGLE PLACES API
        GooglePlacesSearchImpl placesSearch = new GooglePlacesSearchImpl();
        Collection<Model> googleNearbySearch = placesSearch.nearbySearch(modelWithNonCyrCityName, "uk");
        System.out.println("googleNearbySearch size" + Utils.countNonCyrillic(googleNearbySearch));

        //GOOGLE MAPS API
        GoogleMapsSearchImpl googleMaps = new GoogleMapsSearchImpl();
        Collection<Model> geoDecodedModelList = googleMaps.searchCityNameByCoordinatesUsingGoogleMaps(googleNearbySearch);
        System.out.println("geoDecodedModelList count non cyr " + Utils.countNonCyrillic(geoDecodedModelList));

        //REMOVE FROM MODEL LIST ENTITY WITH NON_CYR CITY NAME
        Utils.removeEntityWithNonCyrCityName(geoDecodedModelList);
        System.out.println("model size after remove non cyr entity -> " + geoDecodedModelList.size());
        System.out.println("non cyr size " + Utils.countNonCyrillic(geoDecodedModelList));

        //UNION TWO COLLECTION (MODEL WITH GOOGLE COLLECTION)
        models.addAll(geoDecodedModelList);

        //SPRING START
        //save model
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ModelRepository cityDao = context.getBean(ModelRepository.class);
        List<Model> modelList = cityDao.save(models);
        System.out.println("final size: " + modelList.size());

        //save alter table
//        AlternativeRepository repository = context.getBean("alterRepository", AlternativeRepository.class);
//        Collection<AlternativeModel> optimizedAlternativeNamesList = ScannerFileImpl.getOptimizedAlternativeNamesList(ScannerFileImpl.findAlternativeRegions(PARSE_ALTER_NAME_DB));
//        repository.save(optimizedAlternativeNamesList);

        long finish = System.currentTimeMillis();
        System.out.println("time for save -->" + (finish - start) / 1000 + " sec");
    }
}

