package com.lunapps;

import com.lunapps.configuration.AppConfig;
import com.lunapps.model.Model;
import com.lunapps.repository.AlternativeRepository;
import com.lunapps.repository.ModelRepository;
import com.lunapps.sevice.impl.ScannerFileImpl;
import com.lunapps.utils.Utils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.Collection;

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
        Collection<Model> models = scannerfile.scan2Path(PARSE_UKR_DB1, PARSE_UKR_DB);
        System.out.println(models.size());
        System.out.println("non cyr" + Utils.countNonCyrillic(models));
//

        //TRANSLIT
        Utils.transliterate(models);
        System.out.println("========non cyrillic========== ");
        Utils.countNonCyrillic(models);
        System.out.println("========List size========== " + models.size());


        //SPRING START
        long start = System.currentTimeMillis();

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ModelRepository cityDao = context.getBean("modelRepository", ModelRepository.class);
        cityDao.save(models);

        AlternativeRepository repository = context.getBean("alterRepository", AlternativeRepository.class);
        ScannerFileImpl scannerFile = new ScannerFileImpl();
        repository.save(scannerFile.findAlternativeRegions(PARSE_UKR_DB));

        long finish = System.currentTimeMillis();
        System.out.println("time for save" + (finish - start));
    }
}

