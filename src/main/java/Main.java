import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import model.Model;
import model.RegionInfo;
import utils.DownloadFile;
import utils.ScannerFile;
import utils.UnzipFIle;
import utils.impl.DownloadFileImpl;
import utils.impl.ScannerFileImpl;
import utils.impl.UnzipFIleImpl;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
//        final String SCANNER_PATH = "src/main/resources/UA.txt";
//        final String SCANNER_PATH = "src/main/resources/UA_test.txt";
        final String SCANNER_PATH = "src/main/resources/UA_test_test.txt";
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

        //Sorting
        models.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
        int size = models.size();
        System.out.println("List size ===== " + size);
    }
}

