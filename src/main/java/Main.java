import model.Model;
import utils.ScannerUtils;
import utils.impl.ScannerUtilsImpl;

import java.io.FileNotFoundException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        final String PATH = "src/main/resources/UA.txt";
        ScannerUtils scannerUtils = new ScannerUtilsImpl();
        List<Model> models = scannerUtils.scanPath(PATH);
        scannerUtils.print(models);
    }
}

