package com.lunapps.sevice.impl;

import com.lunapps.model.AlternativeModel;
import com.lunapps.model.Model;
import com.lunapps.model.RegionInfo;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static com.lunapps.config.utils.BaseTestAltModelHelper.getAlternativeModel;
import static com.lunapps.config.utils.BaseTestModelHelper.getModel;
import static com.lunapps.config.utils.BaseTestRegionInfoModelHelper.getRegionInfoModel;
import static org.junit.Assert.*;

public class ScannerFileImplTest {
    private final static String PATH = "src/main/resources/dbtxt/UA.txt";
    private final static String EMPTY_PATH = "";
    private final static String CYR_NAME = "Дніпропетровська область";
    private final static String LATIN_NAME = "Dnipropetrovska Oblast'";
    private final static String NON_CYRILLIC = "non cyrillic";
    private final static String ISO_LANG = "uk";

    private ScannerFileImpl scannerFile;

    @Before
    public void setUp() {
        scannerFile = new ScannerFileImpl();
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_return_exception_scanner_getScanner() {
        //WHEN
        Scanner scanner = ScannerFileImpl.getScanner(EMPTY_PATH);
    }

    @Test
    public void should_return_scanner_getScanner() {
        //WHEN
        Scanner scanner = ScannerFileImpl.getScanner(PATH);

        //THEN
        assertNotNull(scanner);

        //THEN
    }

    @Test(expected = IllegalArgumentException.class)
    public void shoud_return_exception_for_empty_collection_setCityUkrName() {
        //GIVEN
        final int modelCityIndex = 11078435;
        final String modelCityUkrName = "Андріївка";
        final String modelCityInterName = "Andriyivka";
        final double modelLatitude = 48.25781;
        final double modelLongitude = 33.90547;
        final String modelRegionId = "04";
        final String modelRegionCyrName = "";
        final String modelRegionInterName = "";
        final String featureCode = "PPL";

        final String UKR_NAME = "Станція Славута Перша";
        final long GEO_NAME_ID = 11078435;
        //GIVEN
        AlternativeModel aUkrModel = getAlternativeModel(GEO_NAME_ID, ISO_LANG, UKR_NAME);
        Model model = getModel(modelCityIndex, modelCityUkrName, modelCityInterName, modelLatitude, modelLongitude,
                modelRegionId, modelRegionCyrName, modelRegionInterName, featureCode);
        //WHEN
        ScannerFileImpl.setCityUkrName(Collections.EMPTY_LIST, Collections.singletonList(aUkrModel));
        ScannerFileImpl.setCityUkrName(Collections.singletonList(model), Collections.EMPTY_LIST);
        ScannerFileImpl.setCityUkrName(Collections.EMPTY_LIST, Collections.EMPTY_LIST);
        //THEN
        assertNotNull(aUkrModel);
        assertNotNull(model);
        fail("Method should throw IllegalArgumentException");
    }

    @Test
    public void should_set_ukr_city_into_model_setCityUkrName() {
        //GIVEN
        final int modelCityIndex = 11078435;
        final String modelCityUkrName = "Андріївка";
        final String modelCityInterName = "Andriyivka";
        final double modelLatitude = 48.25781;
        final double modelLongitude = 33.90547;
        final String modelRegionId = "04";
        final String modelRegionCyrName = "";
        final String modelRegionInterName = "";
        final String featureCode = "PPL";
        final String UKR_NAME = "Станція Славута Перша";
        final long GEO_NAME_ID = 11078435;

        AlternativeModel aUkrModel = getAlternativeModel(GEO_NAME_ID, ISO_LANG, UKR_NAME);
        Model model = getModel(modelCityIndex, modelCityUkrName, modelCityInterName, modelLatitude, modelLongitude,
                modelRegionId, modelRegionCyrName, modelRegionInterName, featureCode);

        //WHEN
        ScannerFileImpl.setCityUkrName(Collections.singletonList(model), Collections.singletonList(aUkrModel));

        //THEN
        assertNotNull(model);
        assertNotNull(aUkrModel);
        assertNotSame(modelCityUkrName, model.getCityUkrName());
        assertEquals(UKR_NAME, model.getCityUkrName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_return_exception_for_empty_collection_setInterAndCyrRegion() {
        //GIVEN
        final int GEO_CITY_INDEX = 8458701;
        final String REGION_ID = "01";
        final String REG_CYR_NAME = "Одеська область";
        final String REG_INT_NAME = "Odes'ka Oblast'";
        final String FEATURE_CODE = "ADM1";
        RegionInfo regionInfo = getRegionInfoModel(REGION_ID, GEO_CITY_INDEX, REG_CYR_NAME, REG_INT_NAME, FEATURE_CODE);
        Model model = getModel();
        model.setRegionCyrillicName(REG_CYR_NAME);

        //WHEN
        ScannerFileImpl.setInterAndCyrRegion(Collections.EMPTY_LIST, Collections.singletonList(regionInfo));
        ScannerFileImpl.setInterAndCyrRegion(Collections.EMPTY_LIST, Collections.EMPTY_LIST);
        ScannerFileImpl.setInterAndCyrRegion(Collections.singletonList(model), Collections.EMPTY_LIST);

        //THEN
        assertNotNull(model);
        assertNotNull(regionInfo);
        fail("Method should throw IllegalArgumentException");
    }

    @Test
    public void should_set_inter_and_cyr_region_setInterAndCyrRegion() {
        //GIVEN
        final int modelCityIndex = 11078435;
        final String modelCityUkrName = "Андріївка";
        final String modelCityInterName = "Andriyivka";
        final double modelLatitude = 48.25781;
        final double modelLongitude = 33.90547;
        final String modelRegionId = "04";
        final String modelRegionCyrName = "";
        final String modelRegionInterName = "";
        final String featureCode = "PPL";
        final int GEO_CITY_INDEX = 8458701;
        final String REG_ID = "04";
        final String REG_CYR_NAME = "Одеська область";
        final String REG_INT_NAME = "Odes'ka Oblast'";
        final String REG_FEATURE_CODE = "ADM1";

        RegionInfo regionInfo = getRegionInfoModel(REG_ID, GEO_CITY_INDEX, REG_CYR_NAME, REG_INT_NAME, REG_FEATURE_CODE);
        Model model = getModel(modelCityIndex, modelCityUkrName, modelCityInterName, modelLatitude, modelLongitude,
                modelRegionId, modelRegionCyrName, modelRegionInterName, featureCode);

        assertNotNull(regionInfo);
        assertNotNull(model);
        assertNotSame(REG_INT_NAME, model.getRegionInternationalName());
        assertNotSame(REG_CYR_NAME, model.getRegionCyrillicName());

        //WHEN
        scannerFile.setInterAndCyrRegion(Collections.singletonList(model), Collections.singletonList(regionInfo));

        //THEN
        assertEquals(REG_CYR_NAME, model.getRegionCyrillicName());
        assertEquals(REG_INT_NAME, model.getRegionInternationalName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_return_exception_for_empty_collection_setCyrNameIntoRegions() {
        //GIVEN
        final int GEO_CITY_INDEX = 8458701;
        final String REGION_ID = "01";
        final String REG_CYR_NAME = "Одеська область";
        final String REG_INT_NAME = "Odes'ka Oblast'";
        final String FEATURE_CODE = "ADM1";
        AlternativeModel aModel = getAlternativeModel(GEO_CITY_INDEX, ISO_LANG, CYR_NAME);
        RegionInfo regionInfo = getRegionInfoModel(REGION_ID, GEO_CITY_INDEX, REG_CYR_NAME, REG_INT_NAME, FEATURE_CODE);

        //WHEN
        scannerFile.setCyrNameIntoRegions(Collections.singletonList(aModel), Collections.EMPTY_LIST);
        scannerFile.setCyrNameIntoRegions(Collections.EMPTY_LIST, Collections.singletonList(regionInfo));
        scannerFile.setCyrNameIntoRegions(Collections.EMPTY_LIST, Collections.EMPTY_LIST);

        //THEN
        assertNotNull(regionInfo);
        assertNotNull(aModel);
        fail("Method should throw IllegalArgumentException");
    }

    @Test
    public void should_return_region_with_new_cyr_name_region_setCyrNameIntoRegions() {
        //GIVEN
        final int GEO_CITY_INDEX = 8458701;
        final String REGION_ID = "01";
        final String REG_CYR_NAME = "Одеська область";
        final String REG_INT_NAME = "Odes'ka Oblast'";
        final String FEATURE_CODE = "ADM1";

        AlternativeModel aModel = getAlternativeModel(GEO_CITY_INDEX, ISO_LANG, CYR_NAME);
        RegionInfo regionInfo = getRegionInfoModel(REGION_ID, GEO_CITY_INDEX, REG_CYR_NAME, REG_INT_NAME, FEATURE_CODE);
        assertNotNull(aModel);
        assertNotNull(regionInfo);

        //WHEN
        scannerFile.setCyrNameIntoRegions(Collections.singletonList(aModel), Collections.singletonList(regionInfo));

        //THEN
        assertNotSame(REG_CYR_NAME, regionInfo.getRegionCyrillicName());
        assertEquals(CYR_NAME, regionInfo.getRegionCyrillicName());
        assertEquals(REGION_ID, regionInfo.getRegionId());
        assertEquals(REG_INT_NAME, regionInfo.getRegionInternationalName());
        assertEquals(FEATURE_CODE, regionInfo.getFeatureCode());
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_return_exception_for_empty_path_findAlternativeRegions() {
        //GIVEN

        //WHEN
        scannerFile.findAlternativeRegions(EMPTY_PATH);

        //THEN
        fail("Method should throw IllegalArgumentException");
    }

    @Test
    public void should_return_alternativeRegions_findAlternativeRegions() {
        //GIVEN
        final int EXPECTED_SIZE = 7;
        final int EXPECTED_MATCH_FOR_468196 = 2;
        final int EXPECTED_MATCH_FOR_471101 = 1;
        final int EXPECTED_MATCH_FOR_492094 = 1;
        final int EXPECTED_MATCH_FOR_8458701 = 3;
        final String ALT_DB_PATH = "src/main/resources/dbtxt/alt_names/alternativeNamesTest.txt";
        final String EXPECTED_CITY_UKR_NAME = "Катеринівка";
        final String EXPECTED_CITY_UKR_NAME1 = "Всеволожськ";
        final String EXPECTED_CITY_UKR_NAME2 = "Слов'янськ на Кубані";
        final String EXPECTED_CITY_UKR_NAME3 = "Станція Славута Перша";
        final String EXPECTED_CITY_UKR_NAME4 = "Станція Славута I";
        final String EXPECTED_CITY_UKR_NAME5 = "Славута I";

        //WHEN
        List<AlternativeModel> aModelList = ScannerFileImpl.findAlternativeRegions(ALT_DB_PATH);

        //THEN
        assertNotNull(aModelList);
        assertEquals(aModelList.size(), EXPECTED_SIZE);
        assertEquals(EXPECTED_CITY_UKR_NAME, aModelList.get(0).getCyrillicName());
        assertEquals(EXPECTED_CITY_UKR_NAME, aModelList.get(1).getCyrillicName());
        assertEquals(EXPECTED_CITY_UKR_NAME1, aModelList.get(2).getCyrillicName());
        assertEquals(EXPECTED_CITY_UKR_NAME2, aModelList.get(3).getCyrillicName());
        assertEquals(EXPECTED_CITY_UKR_NAME3, aModelList.get(4).getCyrillicName());
        assertEquals(EXPECTED_CITY_UKR_NAME4, aModelList.get(5).getCyrillicName());
        assertEquals(EXPECTED_CITY_UKR_NAME5, aModelList.get(6).getCyrillicName());
        assertEquals(EXPECTED_MATCH_FOR_468196, count(aModelList, aModelList.get(0).getGeoNameId()));
        assertEquals(EXPECTED_MATCH_FOR_471101, count(aModelList, aModelList.get(2).getGeoNameId()));
        assertEquals(EXPECTED_MATCH_FOR_492094, count(aModelList, aModelList.get(3).getGeoNameId()));
        assertEquals(EXPECTED_MATCH_FOR_8458701, count(aModelList, aModelList.get(4).getGeoNameId()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_return_exception_if_path_empty_findRegions() {
        //GIVEN

        //WHEN
        scannerFile.findRegions(EMPTY_PATH);

        //THEN
        fail("Method should throw IllegalArgumentException");
    }

    @Test
    public void should_return_one_region_with_feature_code_ADM1_findRegions() {
        //GIVEN
        final String FILE_PATH_ONE_REGION = "src/main/resources/dbtxt/find_regions/one_adm1.txt";
        final String CYR_NAME = "Дніпропетровська область";
        final String INT_NAME = "Dnipropetrovska Oblast'";
        final String REGION_ID = "04";
        final int ACTUAL_SIZE = 1;
        final int CITY_INDEX = 709929;

        //WHEN
        List<RegionInfo> regionCodes = scannerFile.findRegions(FILE_PATH_ONE_REGION);

        //THEN
        assertEquals(ACTUAL_SIZE, regionCodes.size());
        assertEquals(CYR_NAME, regionCodes.get(0).getRegionCyrillicName());
        assertEquals(INT_NAME, regionCodes.get(0).getRegionInternationalName());
        assertEquals(REGION_ID, regionCodes.get(0).getRegionId());
        assertEquals(CITY_INDEX, regionCodes.get(0).getCityIndex());
    }

    @Test
    public void should_return_regions_with_feature_code_ADM1_findRegions() {
        //GIVEN
        final int EXPECTED_REGIONS_SIZE = 27;
        final String EXPECTED_FEATURE_CODE = "ADM1";

        //WHEN
        List<RegionInfo> foundRegions = scannerFile.findRegions(PATH);

        //THEN
        assertNotNull(foundRegions);
        assertEquals(EXPECTED_REGIONS_SIZE, foundRegions.size());
        assertEquals(EXPECTED_FEATURE_CODE, foundRegions.get(0).getFeatureCode());
        assertTrue(ScannerFileImpl.languageCheck(foundRegions.get(0).getRegionCyrillicName()));
        assertEquals(EXPECTED_FEATURE_CODE, foundRegions.get(1).getFeatureCode());
        assertTrue(ScannerFileImpl.languageCheck(foundRegions.get(1).getRegionCyrillicName()));
        assertFalse(ScannerFileImpl.languageCheck(foundRegions.get(1).getRegionInternationalName()));
        assertEquals(EXPECTED_FEATURE_CODE, foundRegions.get(2).getFeatureCode());
        assertTrue(ScannerFileImpl.languageCheck(foundRegions.get(2).getRegionCyrillicName()));
        assertFalse(ScannerFileImpl.languageCheck(foundRegions.get(2).getRegionInternationalName()));
        assertEquals(EXPECTED_FEATURE_CODE, foundRegions.get(3).getFeatureCode());
        assertTrue(ScannerFileImpl.languageCheck(foundRegions.get(3).getRegionCyrillicName()));
        assertFalse(ScannerFileImpl.languageCheck(foundRegions.get(3).getRegionInternationalName()));
        assertEquals(EXPECTED_FEATURE_CODE, foundRegions.get(4).getFeatureCode());
        assertTrue(ScannerFileImpl.languageCheck(foundRegions.get(4).getRegionCyrillicName()));
        assertFalse(ScannerFileImpl.languageCheck(foundRegions.get(4).getRegionInternationalName()));
        assertEquals(EXPECTED_FEATURE_CODE, foundRegions.get(5).getFeatureCode());
        assertTrue(ScannerFileImpl.languageCheck(foundRegions.get(5).getRegionCyrillicName()));
        assertFalse(ScannerFileImpl.languageCheck(foundRegions.get(5).getRegionInternationalName()));
        assertEquals(EXPECTED_FEATURE_CODE, foundRegions.get(6).getFeatureCode());
        assertTrue(ScannerFileImpl.languageCheck(foundRegions.get(6).getRegionCyrillicName()));
        assertFalse(ScannerFileImpl.languageCheck(foundRegions.get(6).getRegionInternationalName()));
        assertEquals(EXPECTED_FEATURE_CODE, foundRegions.get(7).getFeatureCode());
        assertTrue(ScannerFileImpl.languageCheck(foundRegions.get(7).getRegionCyrillicName()));
        assertFalse(ScannerFileImpl.languageCheck(foundRegions.get(7).getRegionInternationalName()));
        assertEquals(EXPECTED_FEATURE_CODE, foundRegions.get(8).getFeatureCode());
        assertTrue(ScannerFileImpl.languageCheck(foundRegions.get(8).getRegionCyrillicName()));
        assertFalse(ScannerFileImpl.languageCheck(foundRegions.get(8).getRegionInternationalName()));
        assertEquals(EXPECTED_FEATURE_CODE, foundRegions.get(9).getFeatureCode());
        assertTrue(ScannerFileImpl.languageCheck(foundRegions.get(9).getRegionCyrillicName()));
        assertFalse(ScannerFileImpl.languageCheck(foundRegions.get(9).getRegionInternationalName()));
        assertEquals(EXPECTED_FEATURE_CODE, foundRegions.get(10).getFeatureCode());
        assertTrue(ScannerFileImpl.languageCheck(foundRegions.get(10).getRegionCyrillicName()));
        assertFalse(ScannerFileImpl.languageCheck(foundRegions.get(10).getRegionInternationalName()));
        assertEquals(EXPECTED_FEATURE_CODE, foundRegions.get(11).getFeatureCode());
        assertTrue(ScannerFileImpl.languageCheck(foundRegions.get(11).getRegionCyrillicName()));
        assertFalse(ScannerFileImpl.languageCheck(foundRegions.get(11).getRegionInternationalName()));
        assertEquals(EXPECTED_FEATURE_CODE, foundRegions.get(12).getFeatureCode());
        assertTrue(ScannerFileImpl.languageCheck(foundRegions.get(12).getRegionCyrillicName()));
        assertFalse(ScannerFileImpl.languageCheck(foundRegions.get(12).getRegionInternationalName()));
        assertEquals(EXPECTED_FEATURE_CODE, foundRegions.get(13).getFeatureCode());
        assertTrue(ScannerFileImpl.languageCheck(foundRegions.get(13).getRegionCyrillicName()));
        assertFalse(ScannerFileImpl.languageCheck(foundRegions.get(13).getRegionInternationalName()));
        assertEquals(EXPECTED_FEATURE_CODE, foundRegions.get(14).getFeatureCode());
        assertTrue(ScannerFileImpl.languageCheck(foundRegions.get(14).getRegionCyrillicName()));
        assertFalse(ScannerFileImpl.languageCheck(foundRegions.get(14).getRegionInternationalName()));
        assertEquals(EXPECTED_FEATURE_CODE, foundRegions.get(15).getFeatureCode());
        assertTrue(ScannerFileImpl.languageCheck(foundRegions.get(15).getRegionCyrillicName()));
        assertFalse(ScannerFileImpl.languageCheck(foundRegions.get(15).getRegionInternationalName()));
        assertEquals(EXPECTED_FEATURE_CODE, foundRegions.get(16).getFeatureCode());
        assertTrue(ScannerFileImpl.languageCheck(foundRegions.get(16).getRegionCyrillicName()));
        assertFalse(ScannerFileImpl.languageCheck(foundRegions.get(16).getRegionInternationalName()));
        assertEquals(EXPECTED_FEATURE_CODE, foundRegions.get(17).getFeatureCode());
        assertTrue(ScannerFileImpl.languageCheck(foundRegions.get(17).getRegionCyrillicName()));
        assertFalse(ScannerFileImpl.languageCheck(foundRegions.get(17).getRegionInternationalName()));
        assertEquals(EXPECTED_FEATURE_CODE, foundRegions.get(18).getFeatureCode());
        assertTrue(ScannerFileImpl.languageCheck(foundRegions.get(18).getRegionCyrillicName()));
        assertFalse(ScannerFileImpl.languageCheck(foundRegions.get(18).getRegionInternationalName()));
        assertEquals(EXPECTED_FEATURE_CODE, foundRegions.get(19).getFeatureCode());
        assertTrue(ScannerFileImpl.languageCheck(foundRegions.get(19).getRegionCyrillicName()));
        assertFalse(ScannerFileImpl.languageCheck(foundRegions.get(19).getRegionInternationalName()));
        assertEquals(EXPECTED_FEATURE_CODE, foundRegions.get(20).getFeatureCode());
        assertTrue(ScannerFileImpl.languageCheck(foundRegions.get(20).getRegionCyrillicName()));
        assertFalse(ScannerFileImpl.languageCheck(foundRegions.get(20).getRegionInternationalName()));
        assertEquals(EXPECTED_FEATURE_CODE, foundRegions.get(21).getFeatureCode());
        assertTrue(ScannerFileImpl.languageCheck(foundRegions.get(21).getRegionCyrillicName()));
        assertFalse(ScannerFileImpl.languageCheck(foundRegions.get(21).getRegionInternationalName()));
        assertEquals(EXPECTED_FEATURE_CODE, foundRegions.get(22).getFeatureCode());
        assertTrue(ScannerFileImpl.languageCheck(foundRegions.get(22).getRegionCyrillicName()));
        assertFalse(ScannerFileImpl.languageCheck(foundRegions.get(22).getRegionInternationalName()));
        assertEquals(EXPECTED_FEATURE_CODE, foundRegions.get(23).getFeatureCode());
        assertTrue(ScannerFileImpl.languageCheck(foundRegions.get(23).getRegionCyrillicName()));
        assertFalse(ScannerFileImpl.languageCheck(foundRegions.get(23).getRegionInternationalName()));
        assertEquals(EXPECTED_FEATURE_CODE, foundRegions.get(24).getFeatureCode());
        assertTrue(ScannerFileImpl.languageCheck(foundRegions.get(24).getRegionCyrillicName()));
        assertFalse(ScannerFileImpl.languageCheck(foundRegions.get(24).getRegionInternationalName()));
        assertEquals(EXPECTED_FEATURE_CODE, foundRegions.get(25).getFeatureCode());
        assertTrue(ScannerFileImpl.languageCheck(foundRegions.get(25).getRegionCyrillicName()));
        assertFalse(ScannerFileImpl.languageCheck(foundRegions.get(25).getRegionInternationalName()));
        assertEquals(EXPECTED_FEATURE_CODE, foundRegions.get(26).getFeatureCode());
        assertTrue(ScannerFileImpl.languageCheck(foundRegions.get(26).getRegionCyrillicName()));
        assertFalse(ScannerFileImpl.languageCheck(foundRegions.get(26).getRegionInternationalName()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_return_exception_if_string_empty_languageCheck() {
        //GIVEN
        final String emptyString = "";

        //WHEN
        scannerFile.languageCheck(emptyString);

        //THEN
        fail("Method should throw IllegalArgumentException");
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_return_exception_if_string_null_languageCheck() {
        //GIVEN
        final String nullString = null;
        //WHEN
        ScannerFileImpl.languageCheck(nullString);
        //THEN
        fail("Method should throw IllegalArgumentException");
    }

    @Test
    public void should_return_is_true_after_string_languageCheck() {
        //GIVEN

        //WHEN
        boolean cyrName = scannerFile.languageCheck(CYR_NAME);
        boolean latinName1 = scannerFile.languageCheck(LATIN_NAME);
        boolean latinName2 = scannerFile.languageCheck(NON_CYRILLIC);
        boolean latinName3 = scannerFile.languageCheck(ISO_LANG);

        //THEN
        assertNotNull(cyrName);
        assertNotNull(latinName1);
        assertNotNull(latinName2);
        assertNotNull(latinName3);
        assertTrue(cyrName);
        assertFalse(latinName1);
        assertFalse(latinName2);
        assertFalse(latinName3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_return_exception_for_empty_array_languageCheck() {
        //GIVEN
        final String[] emptyArray = {};

        //WHEN
        scannerFile.languageCheck(emptyArray);

        //THEN
        fail("Method should throw IllegalArgumentException");
    }

    @Test
    public void should_return_first_cyrillic_word_or_non_cyrillic_languageCheck() {
        //GIVEN
        final String[] wordsArray = {LATIN_NAME, CYR_NAME};
        final String[] latinWord = {LATIN_NAME};
        final String[] emptyString = {""};

        //WHEN
        String checkedWord = scannerFile.languageCheck(wordsArray);
        String checkedLatinWord = scannerFile.languageCheck(latinWord);
        String checkedEmptyString = scannerFile.languageCheck(emptyString);

        //THEN
        assertNotNull(checkedWord);
        assertEquals(CYR_NAME, checkedWord);

        assertNotNull(checkedLatinWord);
        assertEquals(NON_CYRILLIC, checkedLatinWord);

        assertNotNull(checkedEmptyString);
        assertEquals(NON_CYRILLIC, checkedEmptyString);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_return_exception_getOptimizedAlternativeNamesList() {
        //GIVEN

        //WHEN
        scannerFile.getOptimizedAlternativeNamesList(Collections.EMPTY_LIST);

        //THEN
        fail("Method should throw IllegalArgumentException");
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_return_exception_parserFileToModel() {
        //GIVEN
        final String EMPTY_STRING = "";
        final String NULL_STRING = null;

        //WHEN
        scannerFile.parserFileToModel(EMPTY_STRING);
        scannerFile.parserFileToModel(NULL_STRING);

        //THEN
        fail("Method should throw IllegalArgumentException");
    }

    @Test()
    public void should_return_model_from_txt_file_parserFileToModel() {
        //GIVEN
        final int EXPECTED_SIZE = 4;
        final String FILE_PATH_ONE_REGION = "src/main/resources/dbtxt/parse_file/parse.txt";

        //WHEN
        List<Model> models = scannerFile.parserFileToModel(FILE_PATH_ONE_REGION);
        //THEN
        assertNotNull(models);
        assertEquals(EXPECTED_SIZE, models.size());
        assertEquals("ADM1", models.get(0).getFeatureCode());
        assertEquals(703446, models.get(0).getCityIndex());
        assertEquals("Kyiv Oblast", models.get(0).getInternationalName());
        assertEquals("Кіеўская вобласць", models.get(0).getCityUkrName());
        assertEquals(new Double(50.25), models.get(0).getLatitude());
        assertEquals(new Double(30.5), models.get(0).getLongitude());

        assertEquals("PPLC", models.get(1).getFeatureCode());
        assertEquals(703448, models.get(1).getCityIndex());
        assertEquals("Kiev", models.get(1).getInternationalName());
        assertEquals("Кꙑѥвъ", models.get(1).getCityUkrName());
        assertEquals(new Double(50.45466), models.get(1).getLatitude());
        assertEquals(new Double(30.5238), models.get(1).getLongitude());

        assertEquals("PPLA", models.get(2).getFeatureCode());
        assertEquals(687700, models.get(2).getCityIndex());
        assertEquals("Zaporizhia", models.get(2).getInternationalName());
        assertEquals("Запоріжжя", models.get(2).getCityUkrName());
        assertEquals(new Double(47.82289), models.get(2).getLatitude());
        assertEquals(new Double(35.19031), models.get(2).getLongitude());

        assertEquals("PPL", models.get(3).getFeatureCode());
        assertEquals(687703, models.get(3).getCityIndex());
        assertEquals("Zapillya", models.get(3).getInternationalName());
        assertEquals("Запілля", models.get(3).getCityUkrName());
        assertEquals(new Double(51.13277), models.get(3).getLatitude());
        assertEquals(new Double(28.09011), models.get(3).getLongitude());
    }

    //UTILS METHODS
    private int count(List<AlternativeModel> list, long id) {
        if (CollectionUtils.isEmpty(list)) throw new IllegalArgumentException("list can not be null or empty");
        int count = 0;
        for (AlternativeModel model : list) {
            if (model.getGeoNameId() == id) {
                count++;
            }
        }
        return count;
    }
}