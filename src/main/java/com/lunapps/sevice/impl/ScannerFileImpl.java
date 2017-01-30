package com.lunapps.sevice.impl;

import com.lunapps.model.Model;
import com.lunapps.model.RegionInfo;
import com.lunapps.sevice.ScannerFile;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static java.util.Objects.nonNull;

@Service
@Transactional
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
    /**
     * @param feature code - element in array which can help find
     * administrative division
     * @link http://www.geonames.org/export/codes.html
     */
    private final static int FEATURE_CODE = 7;
    /**
     * @param first-order administrative division
     * @link http://www.geonames.org/export/codes.html
     */
    private final static String GEO_NAME_ADMINISTRATE_DIVISION_CODE = "ADM1";
    /**
     * @param populated place.
     * @link http://www.geonames.org/export/codes.html
     */
    private final static String GEO_NAME_POPULATED_PLACE = "PPL";


    public List<Model> scanPath(String filepath) {
        if (!nonNull(filepath)) throw new IllegalArgumentException("Filepath can not be null");
        Scanner scanner = getScanner(filepath);
        List<RegionInfo> regionCodes = ScannerFileImpl.findRegionCodes(SCANNER_PATH);

        print(regionCodes);
        System.out.println("=========================");

        List<Model> models = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] splitLine = line.split(TABULATION);

            if (!(Objects.equals(splitLine[FEATURE_CODE], GEO_NAME_ADMINISTRATE_DIVISION_CODE) || Objects.equals(splitLine[FEATURE_CODE], GEO_NAME_POPULATED_PLACE))) {
                continue;
            } else {
                Model model = new Model();
                model.setCityIndex(Integer.parseInt(splitLine[CITY_INDEX]));
                model.setLatitude(Double.parseDouble(splitLine[LATITUDE]));
                model.setLongitude(Double.parseDouble(splitLine[LONGITUDE]));
                model.setRegionId(splitLine[REGION_ID]);
                model.setInternationalName((splitLine[INTERNATIONAL_NAME]));
                String[] splitedName = splitLine[NAME].split(COMMA);
                if (splitedName.length == 0) {
                    for (RegionInfo element : regionCodes) {
                        if (Objects.equals(model.getRegionId(), element.getRegionId())) {
                            model.setCityCyrillicName(element.getRegionCyrillicName());
                            model.setCityInternationalName(element.getRegionInternationalName());
                            model.setName(element.getRegionCyrillicName());
                        }
                    }
                    models.add(model);
                } else {
                    model.setName(languageCheck(splitedName));
                    for (RegionInfo element : regionCodes) {
                        if (Objects.equals(model.getRegionId(), element.getRegionId())) {
                            model.setCityCyrillicName(element.getRegionCyrillicName());
                            model.setCityInternationalName(element.getRegionInternationalName());
                        }
                    }
                    models.add(model);
                }
            }
        }
        scanner.close();
        return models;
    }

    private static Scanner getScanner(String filepath) {
        File file = new File(filepath);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Filepath or file does not exist");
        }
        return scanner;
    }


    public static List<RegionInfo> findRegionCodes(String filePath) {
        if (!nonNull(filePath)) throw new IllegalArgumentException("Filepath can not be null");
        Scanner scanner = getScanner(filePath);

        List<RegionInfo> models = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] splitLine = line.split(TABULATION);

            RegionInfo model = new RegionInfo();
            if (splitLine[FEATURE_CODE].equals(GEO_NAME_ADMINISTRATE_DIVISION_CODE)) {
                model.setRegionId(splitLine[REGION_ID]);
                model.setRegionInternationalName(splitLine[INTERNATIONAL_NAME]);
                model.setCityIndex(Integer.parseInt(splitLine[CITY_INDEX]));
                String[] splitedStrings = splitLine[NAME].split(COMMA);
                model.setRegionCyrillicName(languageCheck(splitedStrings));
            }
            if (nonNull(model.getRegionCyrillicName())) {
                models.add(model);
            }
        }
        scanner.close();
        return models;
    }

    private static String languageCheck(String[] splittedLine) {
        if (!nonNull(splittedLine)) throw new IllegalArgumentException("String for language check can not be null");
        String checkedName = "";
        for (int i = splittedLine.length - 1; i >= 0; i--) {
            if (splittedLine[i].codePoints().anyMatch(
                    c -> Character.UnicodeScript.of(c) == Character.UnicodeScript.CYRILLIC))
                return checkedName = splittedLine[i];
        }
        return checkedName = "non cyrillic";
    }

    public void print(List<?> modelList) {
        CollectionUtils.isEmpty(modelList);
        for (Object o : modelList) {
            System.out.println(o);
        }
    }
}
