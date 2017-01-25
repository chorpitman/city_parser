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
import static java.util.Objects.requireNonNull;

public class ScannerFileImpl implements ScannerFile {
    private final static String TABULATION = "\\t";
    private final static String COMMA = ",";
    private final static int CITY_INDEX = 0;
    private final static int INTERNATIONAL_NAME = 2;
    private final static int NAME = 3;
    private final static int LATITUDE = 4;
    private final static int LONGITUDE = 5;
    private final static int REGION_ID = 10;

    public List<Model> scanPath(String filepath) {
        File file = new File(filepath);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Filepath or file does not exist");
        }
        List<RegionInfo> list = ScannerFileImpl.scanPathTestQWE();

        List<Model> models = new ArrayList<Model>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] splitLine = line.split(TABULATION);

            Model model = new Model();
            model.setCityIndex(Integer.parseInt(splitLine[CITY_INDEX]));
            model.setInternationalName((splitLine[INTERNATIONAL_NAME]));

            //create method which can check each element of array
            String stringLine = splitLine[NAME];
            String languageCheckedModelName = languageCheck(stringLine, COMMA);

//            String[] splintedStrings = stringLine.split(COMMA);

//            model.setName(splintedStrings[splintedStrings.length - 1]);
            model.setName(languageCheckedModelName);
            model.setLatitude(Double.parseDouble(splitLine[LATITUDE]));
            model.setLongitude(Double.parseDouble(splitLine[LONGITUDE]));
            model.setRegionId(splitLine[REGION_ID]);

            String regionId = model.getRegionId();
            for (RegionInfo element : list) {
                if (Objects.equals(regionId, element.getRegionId())) {
                    model.setRegion(element.getRegionName());
                }
            }
            models.add(model);
        }
        scanner.close();
        return models;
    }

    private static List<RegionInfo> scanPathTestQWE() {
        final String SCANNER_PATH = "src/main/resources/UA.txt";

        final String TABULATION = "\\t";
        final String COMMA = ",";

        final int INTERNATIONAL_NAME = 2;
        final int NAME = 3;
        final int REGION_ID = 10;
        final int ADMIN_OKRUG = 7;
        final String GEO_NAME_FEATURE_CODE = "ADM1";

        File file = new File(SCANNER_PATH);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Filepath or file does not exist");
        }
        List<RegionInfo> models = new ArrayList<RegionInfo>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] splitLine = line.split(TABULATION);

            RegionInfo model = new RegionInfo();
            if (splitLine[ADMIN_OKRUG].equals(GEO_NAME_FEATURE_CODE)) {
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

    public void print(List<?> modelList) {
        CollectionUtils.isEmpty(modelList);
        for (Object o : modelList) {
            System.out.println(o);
        }
    }

    public String languageCheck(String line, String splitString) {
        nonNull(line);
        nonNull(splitString);
        String[] splittedLine = line.split(splitString);
        String INFO = "";
        for (int i = splittedLine.length - 1; i >= 0; i--) {
            boolean b = containsHanScript(splittedLine[i]);
            if (!b) {
                return INFO = splittedLine[i];
            }
//            if (!splittedLine[i].codePoints().anyMatch(
//                    c -> Character.UnicodeScript.of(c) == Character.UnicodeScript.HAN)) return INFO = splittedLine[i];
        }
        return INFO;
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

    //remove from interface
    @Override
    public List<?> scanPathTest(String filepath) {
        return null;
    }
}
