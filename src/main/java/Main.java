import model.Model;
import model.ModelTest;
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
//        final String SCANNER_PATH = "src/main/resources/UA.txt";
        final String SCANNER_PATH = "src/main/resources/UA_test.txt";
        final String DOWNLOAD_URL = "http://download.geonames.org/export/dump/UA.zip";
        final String DOWNLOAD_DIRECTORY = "src/main/resources/downloads";
        final String ZIP_FILE_PATH = "src/main/resources/downloads/UA.zip";
        final String UNZIP_FILEPATH = "src/main/resources/";

        String s = "xxx已下架xxx";

        System.out.println(containsHanScript(s));



        //DOWNLOAD FILE
//        DownloadFile downloadFile = new DownloadFileImpl();
//        downloadFile.downloadFile(DOWNLOAD_URL, DOWNLOAD_DIRECTORY);


        //UNZIP FILE
//        UnzipFIle unzipFIle = new UnzipFIleImpl();
//        unzipFIle.unzip(ZIP_FILE_PATH, UNZIP_FILEPATH);


        //PARSE FILE GET DISTINCT AND REGION CODE
//        ScannerFile ScannerFile = new ScannerFileImpl();
//        List<?> distinctAndCodes = ScannerFile.scanPathTest(SCANNER_PATH);
//        ScannerFile.print(distinctAndCodes);
//        int size = distinctAndCodes.size();
//        System.out.println("List size = " + size);

        //PARSE FILE 1
//        ScannerFile scannerfile = new ScannerFileImpl();
//        List<Model> models = scannerfile.scanPath(SCANNER_PATH);
//        scannerfile.print(models);
//        int size = models.size();
//        System.out.println("List size = " + size);




    }
    public static boolean containsHanScript(String s) {
        for (int i = 0; i < s.length(); ) {
            int codepoint = s.codePointAt(i);
            i += Character.charCount(codepoint);
            if (Character.UnicodeScript.of(codepoint) == Character.UnicodeScript.HAN) {
                return true;
            }
        }
        return false;
    }
}

