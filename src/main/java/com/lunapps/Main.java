package com.lunapps;

import com.lunapps.configuration.AppConfig;
import com.lunapps.model.AlternativeModel;
import com.lunapps.model.Model;
import com.lunapps.model.RegionInfo;
import com.lunapps.repository.AlternativeRepository;
import com.lunapps.repository.ModelRepository;
import com.lunapps.sevice.DownloadFile;
import com.lunapps.sevice.UnzipFIle;
import com.lunapps.sevice.impl.DownloadFileImpl;
import com.lunapps.sevice.impl.ScannerFileImpl;
import com.lunapps.sevice.impl.UnzipFIleImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        final String PARSE_UKR_DB1 = "src/main/resources/UA.txt";
        final String PARSE_UKR_DB = "src/main/resources/alternateNames.txt";
//        final String PARSE_UKR_DB = "src/main/resources/dbtxt/AltNames=Ukr_region";
        final String DOWNLOAD_DIRECTORY = "src/main/resources/downloads";
        final String DOWNLOAD_UKR_DB = "http://download.geonames.org/export/dump/UA.zip";
        final String DOWNLOAD_ALTER_NAMES_DB = "http://download.geonames.org/export/dump/alternateNames.zip";
        final String ZIP_UKR_DB = "src/main/resources/downloads/UA.zip";
        final String ZIP_ALTER_NAMES_DB = "src/main/resources/downloads/alternateNames.zip";
        final String UNZIP_DIRECTORY = "src/main/resources/";

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
//        Collection<Model> models = scannerfile.scanPath(PARSE_UKR_DB);
//        scannerfile.print(models);
//        int size = models.size();
//        System.out.println("List size ===== " + size);
        Collection<Model> models = scannerfile.scan2Path(PARSE_UKR_DB1, PARSE_UKR_DB);
        System.out.println(models.size());
//        //GET REGIONS
//        List<RegionInfo> list = scannerfile.findRegions(PARSE_UKR_DB1);
//        System.out.println(list.size());
//        scannerfile.print(list);
//
//        List<AlternativeModel> regions = scannerfile.findAlternativeRegions(PARSE_UKR_DB);
//        System.out.println(regions.size());
//
//        for (AlternativeModel region : regions) {
//            if (region.getGeoNameId() == 686966) {
//                System.out.println(region);
//            } else if ((region.getGeoNameId() == 687699)) {
//                System.out.println(region);
//            } else if ((region.getGeoNameId() == 687869)) {
//                System.out.println(region);
//            } else if ((region.getGeoNameId() == 689064)) {
//                System.out.println(region);
//            } else if ((region.getGeoNameId() == 689559)) {
//                System.out.println(region);
//            } else if ((region.getGeoNameId() == 691649)) {
//                System.out.println(region);
//            } else if ((region.getGeoNameId() == 692196)) {
//                System.out.println(region);
//            } else if ((region.getGeoNameId() == 694422)) {
//                System.out.println(region);
//            } else if ((region.getGeoNameId() == 695592)) {
//                System.out.println(region);
//            } else if ((region.getGeoNameId() == 696634)) {
//                System.out.println(region);
//            } else if ((region.getGeoNameId() == 698738)) {
//                System.out.println(region);
//            } else if ((region.getGeoNameId() == 700567)) {
//                System.out.println(region);
//            } else if ((region.getGeoNameId() == 702549)) {
//                System.out.println(region);
//            } else if ((region.getGeoNameId() == 702657)) {
//                System.out.println(region);
//            } else if ((region.getGeoNameId() == 703446)) {
//                System.out.println(region);
//            } else if ((region.getGeoNameId() == 703447)) {
//                System.out.println(region);
//            } else if ((region.getGeoNameId() == 703883)) {
//                System.out.println(region);
//            } else if ((region.getGeoNameId() == 705811)) {
//                System.out.println(region);
//            } else if ((region.getGeoNameId() == 706370)) {
//                System.out.println(region);
//            } else if ((region.getGeoNameId() == 706442)) {
//                System.out.println(region);
//            } else if ((region.getGeoNameId() == 706482)) {
//                System.out.println(region);
//            } else if ((region.getGeoNameId() == 707470)) {
//                System.out.println(region);
//            } else if ((region.getGeoNameId() == 709716)) {
//                System.out.println(region);
//            } else if ((region.getGeoNameId() == 709929)) {
//                System.out.println(region);
//            } else if ((region.getGeoNameId() == 710720)) {
//                System.out.println(region);
//            } else if ((region.getGeoNameId() == 710734)) {
//                System.out.println(region);
//            } else if ((region.getGeoNameId() == 710802)) {
//                System.out.println(region);
//            }
//        }
//
//        System.out.println(regions.size());
//
////search alternative name
//        for (RegionInfo info : list) {
//            int cityIndex = info.getCityIndex();
//            for (AlternativeModel model : regions) {
//                if (model.getGeoNameId() == cityIndex) {
//                     if (containsHanScriptStream(model.getCyrillicName())) {
//                         info.setRegionCyrillicName(model.getCyrillicName());
//                     } else {
//                         continue;
//                     }
//                }
//            }
//        }
//        scannerfile.print(list);
        //COUNT CYRILLIC WORDS
//        System.out.println("non cyrillic size ===== " + Utils.countNonCyrillic(models));

        //TRANSLIT
//        Utils.transliterate(models);
//        System.out.println("========non cyrillic========== ");
//        Utils.countNonCyrillic(models);
//        System.out.println("========List size========== " + models.size());

        //SPRING START
        long start = System.currentTimeMillis();

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ModelRepository cityDao = context.getBean("modelRepository", ModelRepository.class);
        cityDao.save(models);

//        AlternativeRepository repository = context.getBean("alterRepository", AlternativeRepository.class);
//        ScannerFileImpl scannerFile = new ScannerFileImpl();
//        repository.save(scannerFile.findAlternativeRegions(PARSE_UKR_DB))

        long finish = System.currentTimeMillis();
        System.out.println("time for save" + (finish - start));
    }

    private static boolean containsHanScriptStream(String s) {
        String INFO = "";
        if (s.codePoints().anyMatch(
                c -> Character.UnicodeScript.of(c) == Character.UnicodeScript.CYRILLIC)) return true;
        else return false;
    }
}

