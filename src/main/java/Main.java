import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
//        final String SCANNER_PATH = "src/main/resources/UA.txt";
        final String SCANNER_PATH = "src/main/resources/UA_test.txt";
        final String DOWNLOAD_URL = "http://download.geonames.org/export/dump/UA.zip";
        final String DOWNLOAD_DIRECTORY = "src/main/resources/downloads";
        final String ZIP_FILE_PATH = "src/main/resources/downloads/UA.zip";
        final String UNZIP_FILEPATH = "src/main/resources/";
        final String COMMA = ",";

        //languages
        final String CHINESE = "xxx已下架xxx";
        final String korean = "드니프로페트로우시크";
        final String chinese = "第聂伯罗彼得罗夫斯克";
        final String japanise = "ドニプロペトロウシク";
        final String georgian = "დნეპროპეტროვსკი";
        final String tai = "ดนีโปรเปตรอฟสค์";
        final String hindi = "द्नेप्रोपेत्रोव्स्क";
        final String ukr = "Дніпропетровська область";

        System.out.println("CHINESE ETALON ->" + containsHanScript(CHINESE));
        System.out.println("korean ->" + containsHanScript(korean));
        System.out.println("chinese ->" + containsHanScript(chinese));
        System.out.println("japanise ->" + containsHanScript(japanise));
        System.out.println("georgian ->" + containsHanScript(georgian));
        System.out.println("tai ->" + containsHanScript(tai));
        System.out.println("hindi ->" + containsHanScript(hindi));
        System.out.println("ukr ->" + containsHanScript(ukr));

        System.out.println("=======================================");

        System.out.println("CHINESE ETALON ->" + containsHanScriptStream(CHINESE, COMMA));
        System.out.println("korean ->" + containsHanScriptStream(korean, COMMA));
        System.out.println("chinese ->" + containsHanScriptStream(chinese, COMMA));
        System.out.println("japanise ->" + containsHanScriptStream(japanise, COMMA));
        System.out.println("georgian ->" + containsHanScriptStream(georgian, COMMA));
        System.out.println("tai ->" + containsHanScriptStream(tai, COMMA));
        System.out.println("hindi ->" + containsHanScriptStream(hindi, COMMA));
        System.out.println("ukr ->" + containsHanScriptStream(ukr, COMMA));

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

    public static boolean containsHanScriptStream(String s, String split) {
        String[] splittedLine = s.split(split);
        String INFO = "";
        for (int i = 0; i < splittedLine.length; i++) {
            if (splittedLine[i].codePoints().anyMatch(
                    c -> Character.UnicodeScript.of(c) == Character.UnicodeScript.HAN)) return true;
        }
        return false;
    }
}

