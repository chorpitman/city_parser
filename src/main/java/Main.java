import model.Model;
import utils.DownloadFile;
import utils.ScannerFile;
import utils.UnzipFIle;
import utils.impl.DownloadFileImpl;
import utils.impl.ScannerFileImpl;
import utils.impl.UnzipFIleImpl;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        final String SCANNER_PATH = "src/main/resources/UA.txt";
        final String DOWNLOAD_URL = "http://download.geonames.org/export/dump/UA.zip";
        final String DOWNLOAD_DIRECTORY = "src/main/resources/downloads";
        final String ZIP_FILE_PATH = "src/main/resources/downloads/UA.zip";
        final String UNZIP_FILEPATH = "src/main/resources/";

        //DOWNLOAD FILE
//        DownloadFile downloadFile = new DownloadFileImpl();
//        downloadFile.downloadFile(DOWNLOAD_URL, DOWNLOAD_DIRECTORY);


        //UNZIP FILE
//        UnzipFIle unzipFIle = new UnzipFIleImpl();
//        unzipFIle.unzip(ZIP_FILE_PATH, UNZIP_FILEPATH);


        //PARSE FILE
        ScannerFile ScannerFile = new ScannerFileImpl();
        List<Model> models = ScannerFile.scanPath(SCANNER_PATH);
        ScannerFile.print(models);
        int size = models.size();
        System.out.println("List size = " + size);
    }
}

