package com.lunapps;

import com.lunapps.model.Model;
import com.lunapps.sevice.DownloadFile;
import com.lunapps.sevice.UnzipFIle;
import com.lunapps.sevice.impl.DownloadFileImpl;
import com.lunapps.sevice.impl.ScannerFileImpl;
import com.lunapps.sevice.impl.UnzipFIleImpl;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        final String SCANNER_PATH = "src/main/resources/UA.txt";
//        final String SCANNER_PATH = "src/main/resources/UA_test.txt";
//        final String SCANNER_PATH = "src/main/resources/UA_test_test.txt";
//        final String SCANNER_PATH = "src/main/resources/UA_test_test_test.txt";
        final String DOWNLOAD_URL = "http://download.geonames.org/export/dump/UA.zip";
        final String DOWNLOAD_DIRECTORY = "src/main/resources/downloads";
        final String ZIP_FILE_DIRECTORY = "src/main/resources/downloads/UA.zip";
        final String UNZIP_DIRECTORY = "src/main/resources/";

        //DOWNLOAD FILE
        DownloadFile downloadFile = new DownloadFileImpl();
        downloadFile.downloadFile(DOWNLOAD_URL, DOWNLOAD_DIRECTORY);

        //UNZIP FILE
        UnzipFIle unzipFIle = new UnzipFIleImpl();
        unzipFIle.unzip(ZIP_FILE_DIRECTORY, UNZIP_DIRECTORY);

        //PARSE FILE 1
        ScannerFileImpl scannerfile = new ScannerFileImpl();
        List<Model> models = scannerfile.scanPath(SCANNER_PATH);
        scannerfile.print(models);
        int size = models.size();
        System.out.println("List size ===== " + size);
    }
}
