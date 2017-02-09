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
    //ARRAYS DELIMITERS
    private final static String TABULATION = "\\t";
    private final static String COMMA = ",";
    //ARRAY MAPPING
    private final static int CITY_INDEX = 0;
    private final static int INTERNATIONAL_NAME = 2;
    private final static int CYRILLIC_NAME = 3;
    private final static int LATITUDE = 4;
    private final static int LONGITUDE = 5;
    private final static int REGION_ID = 10;
    private final static String UNNECESSARY_REGION_ID = "00";
    private final static String NON_CYRILLIC = "non cyrillic";
    private final static int ALTERNATIVE_CITY_INDEX = 1;
    private final static int ISO_LANG = 2;
    private final static int CYRILL_NAME = 3;
    private final static String UKR = "uk";
    private final static int START_UKR_CITY_INDEX = 468196;

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
    private final static String REGION_CODE = "ADM1";

    /**
     * @param populated place.
     * @link http://www.geonames.org/export/codes.html
     */
    private final static String GEO_CODE_PPL = "PPL";

    /**
     * @param capital of a political entity.
     * @link http://www.geonames.org/export/codes.html
     */
    private final static String GEO_CODE_PPLC = "PPLC";

    /**
     * @param seat of a first-order administrative division.
     * @link http://www.geonames.org/export/codes.html
     */
    private final static String GEO_CODE_PPLA = "PPLA";

    @Override
    public Collection<Model> scanPath(final String filepath) {
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
            if (!(Objects.equals(splitLine[FEATURE_CODE], REGION_CODE) || Objects.equals(splitLine[FEATURE_CODE], GEO_CODE_PPL))) {
                continue;
            } else {
                Model model = new Model();
                model.setCityIndex(Integer.parseInt(splitLine[CITY_INDEX]));
                model.setLatitude(Double.parseDouble(splitLine[LATITUDE]));
                model.setLongitude(Double.parseDouble(splitLine[LONGITUDE]));
                model.setRegionId(splitLine[REGION_ID]);
                model.setInternationalName((splitLine[INTERNATIONAL_NAME]));
                String[] splitedName = splitLine[CYRILLIC_NAME].split(COMMA);
                if (splitedName.length == 0) {
                    for (RegionInfo element : regionCodes) {
                        if (Objects.equals(model.getRegionId(), element.getRegionId())) {
                            model.setRegionCyrillicName(element.getRegionCyrillicName());
                            model.setRegionInternationalName(element.getRegionInternationalName());
                            model.setCityUkrName(element.getRegionCyrillicName());
                        }
                    }
                    models.add(model);
                } else {
                    model.setCityUkrName(languageCheck(splitedName));
                    for (RegionInfo element : regionCodes) {
                        if (Objects.equals(model.getRegionId(), element.getRegionId())) {
                            model.setRegionCyrillicName(element.getRegionCyrillicName());
                            model.setRegionInternationalName(element.getRegionInternationalName());
                        }
                    }
                    models.add(model);
                }
            }
        }
        scanner.close();
        return models;
    }

    @Override
    public Collection<Model> scan2Path(final String ukrDbPath, final String alterNameDb) {
        if (StringUtils.isBlank(ukrDbPath) || StringUtils.isBlank(alterNameDb))
            throw new IllegalArgumentException("Filepath can not be null or empty");

        //GET ALTERNATIVE UKR REGIONS
        List<AlternativeModel> nonOptimizeAlternativeNamesList = findAlternativeRegions(alterNameDb);

        //OPTIMIZE ALTERNATIVE UKR REGION
        Collection<AlternativeModel> optimizedAlternativeNamesList = ScannerFileImpl.getOptimizedAlternativeNamesList(nonOptimizeAlternativeNamesList);
        System.out.println("alternative name size = " + optimizedAlternativeNamesList.size());

        //GET UKR REGIONS
        List<RegionInfo> ukrRegionsList = new ScannerFileImpl().findRegions(ukrDbPath);
        System.out.println("regions size = " + ukrRegionsList.size());

        //SET CYRILLIC CYRILLIC_NAME INTO REGIONS
        setCyrNameInRegions(optimizedAlternativeNamesList, ukrRegionsList);
        print(ukrRegionsList);
        System.out.println(ukrRegionsList.size() + "====================================");

        Scanner scanner = getScanner(ukrDbPath);

        //PARSE UKR DB
        // TODO: 2/9/17 think about Array or LinkedList
        List<Model> ukrCitiesModels = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] splitLine = line.split(TABULATION);
            if (Objects.equals(splitLine[REGION_ID], UNNECESSARY_REGION_ID)) {
                continue;
            }
            if (!(Objects.equals(splitLine[FEATURE_CODE], REGION_CODE) ||
                    Objects.equals(splitLine[FEATURE_CODE], GEO_CODE_PPL) ||
                    Objects.equals(splitLine[FEATURE_CODE], GEO_CODE_PPLA) ||
                    Objects.equals(splitLine[FEATURE_CODE], GEO_CODE_PPLC))) {
                continue;
            } else {
                Model model = new Model();
                model.setCityIndex(Integer.parseInt(splitLine[CITY_INDEX]));
                model.setCityUkrName(languageCheck(splitLine[CYRILLIC_NAME].split(COMMA)));
                model.setInternationalName((splitLine[INTERNATIONAL_NAME]));
                model.setLatitude(Double.parseDouble(splitLine[LATITUDE]));
                model.setLongitude(Double.parseDouble(splitLine[LONGITUDE]));
                model.setRegionId(splitLine[REGION_ID]);
                ukrCitiesModels.add(model);
            }
        }
        scanner.close();

        //SET INTO UKR REGIONS INTERNATIONAL AND CYRILLIC CYRILLIC_NAME
        setInterCyrRegion(ukrCitiesModels, ukrRegionsList);

        //SET INTO UKR CITIES UKR CYRILLIC_NAME
        setCityUkrName(ukrCitiesModels, optimizedAlternativeNamesList);

        //SEARCH UKR NAME IN EXIST MODEL BY NON CYRILLIC == INTER == CYR NAME
        findExistCyr(ukrCitiesModels);

        return ukrCitiesModels;
    }

    private void findExistCyr(List<Model> ukrCitiesModels) {
        for (Model ukrModel : ukrCitiesModels) {
            if (ukrModel.getCityUkrName().equals(NON_CYRILLIC)) {
                String ukrModelInternationalName = ukrModel.getInternationalName();
                for (Model element : ukrCitiesModels) {
                    if (ukrModelInternationalName.equals(element.getInternationalName())) {
                        if (languageCheck(element.getCityUkrName())) {
                            ukrModel.setCityUkrName(element.getCityUkrName());
                            break;
                        }
                    }
                }

            }
        }
    }

    private static void setCityUkrName(Collection<Model> ukrCitiesModels, Collection<AlternativeModel> optimizedAlternativeNamesList) {
        if (CollectionUtils.isEmpty(ukrCitiesModels) || CollectionUtils.isEmpty(optimizedAlternativeNamesList))
            throw new IllegalArgumentException("ukrCitiesModels or optimizedAlternativeNamesList can not be null or empty");

        int result = 0;
        for (Model model : ukrCitiesModels) {
            int cityIndex = model.getCityIndex();
            for (AlternativeModel altRegion : optimizedAlternativeNamesList) {
                if (cityIndex == altRegion.getGeoNameId()) {
                    model.setCityUkrName(altRegion.getCyrillicName());
                    result++;
                }
            }
        }
        // TODO: 09.02.2017 delete after testing
        //SHOWS result of changes for DEBUG
        int a = result;
    }

    private static void setInterCyrRegion(Collection<Model> ukrCitiesModels, Collection<RegionInfo> ukrRegionsList) {
        if (CollectionUtils.isEmpty(ukrCitiesModels) || CollectionUtils.isEmpty(ukrRegionsList))
            throw new IllegalArgumentException("ukrCitiesModels or ukrRegionsList can not be null or empty");

        for (Model model : ukrCitiesModels) {
            String regionId = model.getRegionId();

            for (RegionInfo region : ukrRegionsList) {
                if (Objects.equals(regionId, region.getRegionId())) {
                    model.setRegionInternationalName(region.getRegionInternationalName());
                    model.setRegionCyrillicName(region.getRegionCyrillicName());
                }
            }
        }
    }

    private void setCyrNameInRegions(Collection<AlternativeModel> optimizedAlternativeNamesList, Collection<RegionInfo> regions) {
        if (CollectionUtils.isEmpty(optimizedAlternativeNamesList) || CollectionUtils.isEmpty(regions))
            throw new IllegalArgumentException("optimizedAlternativeNamesList or regions List can not be null or empty");

        for (RegionInfo region : regions) {
            int cityIndex = region.getCityIndex();

            for (AlternativeModel alternativeRegion : optimizedAlternativeNamesList) {
                if (alternativeRegion.getGeoNameId() == cityIndex) {
                    if (languageCheck(alternativeRegion.getCyrillicName())) {
                        region.setRegionCyrillicName(alternativeRegion.getCyrillicName());
                        break;
                    } else continue;
                }
            }
        }
    }

    public List<RegionInfo> findRegions(final String filePath) {
        if (StringUtils.isBlank(filePath)) throw new IllegalArgumentException("Filepath can not be null");

        Scanner scanner = getScanner(filePath);
        List<RegionInfo> models = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] splitLine = line.split(TABULATION);

            RegionInfo model = new RegionInfo();
            if (splitLine[FEATURE_CODE].equals(REGION_CODE)) {
                model.setRegionId(splitLine[REGION_ID]);
                model.setCityIndex(Integer.parseInt(splitLine[CITY_INDEX]));
                model.setRegionInternationalName(splitLine[INTERNATIONAL_NAME]);
                String[] splitedStrings = splitLine[CYRILLIC_NAME].split(COMMA);
                model.setRegionCyrillicName(languageCheck(splitedStrings));
                models.add(model);
            }
        }
        scanner.close();
        return models;
    }

    public static List<AlternativeModel> findAlternativeRegions(final String filePath) {
        if (StringUtils.isBlank(filePath)) throw new IllegalArgumentException("Filepath can not be null");

        Scanner scanner = getScanner(filePath);
        List<AlternativeModel> models = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] splitLine = line.split(TABULATION);

            AlternativeModel model = new AlternativeModel();
            if (Integer.parseInt(splitLine[ALTERNATIVE_CITY_INDEX]) >= START_UKR_CITY_INDEX) {
                if (splitLine[ISO_LANG].equals(UKR)) {
                    model.setGeoNameId(Integer.parseInt(splitLine[ALTERNATIVE_CITY_INDEX]));
                    model.setIsoLang(splitLine[ISO_LANG]);
                    if (languageCheck(splitLine[CYRILL_NAME])) {
                        model.setCyrillicName(splitLine[CYRILL_NAME]);
                    } else {
                        // TODO: 09.02.2017 think может просто забывать про эту сущность???
                        //если нет кириллицы добавляем
                        model.setCyrillicName(NON_CYRILLIC);
                    }
                    models.add(model);
                }
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
        return checkedName = NON_CYRILLIC;
    }

    public static boolean languageCheck(final String splittedLine) {
        if (Objects.isNull(splittedLine))
            throw new IllegalArgumentException("String for language check can not be null");

        if (splittedLine.codePoints().anyMatch(
                c -> Character.UnicodeScript.of(c) == Character.UnicodeScript.CYRILLIC)) return true;
        else return false;
    }

    private static Scanner getScanner(final String filepath) {
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
    public void print(Collection<?> modelList) {
        if (CollectionUtils.isEmpty(modelList))
            throw new IllegalArgumentException("Model list cannot be null or Empty");
        for (Object o : modelList) System.out.println(o);
    }

    public static Collection<AlternativeModel> getOptimizedAlternativeNamesList(Collection<AlternativeModel> nonOptimizeList) {
        if (CollectionUtils.isEmpty(nonOptimizeList))
            throw new IllegalArgumentException("nonOptimizeList can not be null or empty");
        // TODO: 09.02.2017 test if we remove copyNonOptimized
        Collection<AlternativeModel> copyNonOptimizeList = new LinkedList<>(nonOptimizeList);
        List<AlternativeModel> optimizedList = new ArrayList<>();
        long result = 0;
        for (AlternativeModel element : nonOptimizeList) {
            long geoNameId = element.getGeoNameId();
            if (result == geoNameId) {
                continue;
            }
            for (AlternativeModel model : copyNonOptimizeList) {
                if (model.getGeoNameId() == geoNameId) {
                    String cyrillicName = model.getCyrillicName();
                    if (ScannerFileImpl.languageCheck(cyrillicName)) {
                        AlternativeModel alternativeModel = new AlternativeModel();
                        alternativeModel.setCyrillicName(cyrillicName);
                        alternativeModel.setGeoNameId(model.getGeoNameId());
                        alternativeModel.setIsoLang(model.getIsoLang());
                        optimizedList.add(alternativeModel);
                        result = geoNameId;
                        break;
                    }
                }
            }
        }
        return optimizedList;
    }
}
