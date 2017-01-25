package utils.impl;

import model.Model;
import model.RegionInfo;
import org.apache.commons.collections.CollectionUtils;
import utils.ScannerFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static java.util.Objects.nonNull;

public class ScannerFileImpl implements ScannerFile {
    private final static String SCANNER_PATH = "src/main/resources/UA.txt";


    private final static String TABULATION = "\\t";
    private final static String COMMA = ",";

    private final static int CITY_INDEX = 0;
    private final static int INTERNATIONAL_NAME = 2;
    private final static int NAME = 3;
    private final static int LATITUDE = 4;
    private final static int LONGITUDE = 5;
    private final static int REGION_ID = 10;
    private final static int ADMIN_REGION = 7;
    private final static String GEO_NAME_FEATURE_CODE = "ADM1";


    public List<Model> scanPath(String filepath) {
        File file = new File(filepath);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Filepath or file does not exist");
        }
        List<RegionInfo> regionCodes = ScannerFileImpl.findRegionCodes();

        List<Model> models = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] splitLine = line.split(TABULATION);

            Model model = new Model();
            model.setCityIndex(Integer.parseInt(splitLine[CITY_INDEX]));
            model.setInternationalName((splitLine[INTERNATIONAL_NAME]));
            //create method which will check each element of array
            String stringLine = splitLine[NAME];
            String languageCheckedModelName = languageCheck(stringLine, COMMA);

//            String[] splintedStrings = stringLine.split(COMMA);

//            model.setName(splintedStrings[splintedStrings.length - 1]);
            model.setName(languageCheckedModelName);
            model.setLatitude(Double.parseDouble(splitLine[LATITUDE]));
            model.setLongitude(Double.parseDouble(splitLine[LONGITUDE]));
            model.setRegionId(splitLine[REGION_ID]);
            for (RegionInfo element : regionCodes) {
                if (Objects.equals(model.getRegionId(), element.getRegionId())) {
                    model.setRegionName(element.getRegionName());
                    // TODO: 25.01.2017 think how to set InternationalName
//                    model.setRegionNameInternational();
                }
            }
            models.add(model);
        }
        scanner.close();
        return models;
    }

    private static List<RegionInfo> findRegionCodes() {
        File file = new File(SCANNER_PATH);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Filepath or file does not exist");
        }
        List<RegionInfo> models = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] splitLine = line.split(TABULATION);

            RegionInfo model = new RegionInfo();

            if (splitLine[ADMIN_REGION].equals(GEO_NAME_FEATURE_CODE)) {
                model.setRegionId(splitLine[REGION_ID]);
                //Splitted line by "," for  separate info
                String stringLine = splitLine[NAME];
                String[] splintedStrings = stringLine.split(COMMA);
                model.setRegionName(splintedStrings[splintedStrings.length - 1]);
                model.setRegionNameInternational(splintedStrings[INTERNATIONAL_NAME]);
            }
            //check for non null model
            if (nonNull(model.getRegionName())) {
                models.add(model);
            }
        }
        scanner.close();
        return models;
    }

    private String languageCheck(String line, String splitString) {
        nonNull(line);
        nonNull(splitString);
        String[] splittedLine = line.split(splitString);
        String INFO = "";
        for (int i = splittedLine.length - 1; i >= 0; i--) {
            if (!splittedLine[i].codePoints().anyMatch(
                    c -> Character.UnicodeScript.of(c) == Character.UnicodeScript.HAN)) return INFO = splittedLine[i];
        }
        return INFO;
    }

    //test
    private static boolean containsHanScript(String s) {
        for (int i = 0; i < s.length(); ) {
            int codepoint = s.codePointAt(i);
            i += Character.charCount(codepoint);
            if (Character.UnicodeScript.of(codepoint) == Character.UnicodeScript.HAN) {
                return true;
            }
        }
        return false;
    }

    public void print(List<?> modelList) {
        CollectionUtils.isEmpty(modelList);
        for (Object o : modelList) {
            System.out.println(o);
        }
    }
}
