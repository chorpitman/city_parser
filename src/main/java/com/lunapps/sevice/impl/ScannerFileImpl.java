package com.lunapps.sevice.impl;

import com.lunapps.model.AlternativeModel;
import com.lunapps.model.Model;
import com.lunapps.model.RegionInfo;
import com.lunapps.sevice.ScannerFile;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Service
@Transactional
public class ScannerFileImpl implements ScannerFile {
    private final static String TABULATION = "\\t";
    private final static String COMMA = ",";

    private final static int CITY_INDEX = 0;
    private final static int INTERNATIONAL_NAME = 2;
    private final static int NAME = 3;
    private final static int LATITUDE = 4;
    private final static int LONGITUDE = 5;
    private final static int REGION_ID = 10;
    private final static String UNNECESSARY_REGION_ID = "00";
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


    public Collection<Model> scanPath(String filepath) {
        if (StringUtils.isBlank(filepath)) throw new IllegalArgumentException("Filepath can not be null");

        Scanner scanner = getScanner(filepath);
        List<RegionInfo> regionCodes = new ScannerFileImpl().findRegions(filepath);

        print(regionCodes);
        System.out.println("=========================");

        List<Model> models = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] splitLine = line.split(TABULATION);
            if (Objects.equals(splitLine[REGION_ID], UNNECESSARY_REGION_ID)) {
                continue;
            }
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

    public List<RegionInfo> findRegions(String filePath) {
        if (StringUtils.isBlank(filePath)) throw new IllegalArgumentException("Filepath can not be null");

        Scanner scanner = getScanner(filePath);
        List<RegionInfo> models = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] splitLine = line.split(TABULATION);

            RegionInfo model = new RegionInfo();
            if (splitLine[FEATURE_CODE].equals(GEO_NAME_ADMINISTRATE_DIVISION_CODE) & !splitLine[REGION_ID].equals(UNNECESSARY_REGION_ID)) {
                model.setRegionId(splitLine[REGION_ID]);
                model.setRegionInternationalName(splitLine[INTERNATIONAL_NAME]);
                model.setCityIndex(Integer.parseInt(splitLine[CITY_INDEX]));
//                String[] splitedStrings = splitLine[NAME].split(COMMA);
//                model.setRegionCyrillicName(languageCheck(splitedStrings));
                models.add(model);
            }
//            if (nonNull(model.getRegionCyrillicName())) {
//            }
        }
        scanner.close();
        return models;
    }

    public List<AlternativeModel> findAlternativeRegions(String filePath) {
        if (StringUtils.isBlank(filePath)) throw new IllegalArgumentException("Filepath can not be null");

        final String UKR = "uk";
        final int CYRILL_NAME = 3;
        final int ISO_LANG = 2;

        final int GEONAME_ID = 1;

        Scanner scanner = getScanner(filePath);
        List<AlternativeModel> models = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] splitLine = line.split(TABULATION);

            AlternativeModel model = new AlternativeModel();
            if (splitLine[ISO_LANG].equals(UKR)) {
                model.setGeoNameId(Integer.parseInt(splitLine[GEONAME_ID]));
                model.setIsoLang(splitLine[ISO_LANG]);
                languageCheck(splitLine);
                model.setCyrillicName(splitLine[CYRILL_NAME]);
                models.add(model);
            }
        }
        scanner.close();
        return models;
    }

    private static String languageCheck(String[] splittedLine) {
        if (Objects.isNull(splittedLine))
            throw new IllegalArgumentException("String for language check can not be null");

        String checkedName = "";

        for (int i = splittedLine.length - 1; i >= 0; i--) {
            if (splittedLine[i].codePoints().anyMatch(
                    c -> Character.UnicodeScript.of(c) == Character.UnicodeScript.CYRILLIC))
                return checkedName = splittedLine[i];
        }
        return checkedName = "non cyrillic";
    }

    private static Scanner getScanner(String filepath) {
        if (StringUtils.isBlank(filepath)) throw new IllegalArgumentException("Filepath can not be null");
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

    @Override
    public void print(java.util.Collection<?> modelList) {
        if (CollectionUtils.isEmpty(modelList))
            throw new IllegalArgumentException("Model list cannot be null or Empty");
        for (Object o : modelList) System.out.println(o);
    }
}
