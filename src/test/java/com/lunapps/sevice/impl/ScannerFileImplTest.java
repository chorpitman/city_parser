package com.lunapps.sevice.impl;

import com.lunapps.model.AlternativeModel;
import com.lunapps.model.Model;
import com.lunapps.model.RegionInfo;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static com.lunapps.config.utils.BaseTestAltModelHelper.getAlternativeModel;
import static com.lunapps.config.utils.BaseTestAltModelHelper.makeDuplicate;
import static com.lunapps.config.utils.BaseTestModelHelper.getModel;
import static com.lunapps.config.utils.BaseTestRegionInfoModelHelper.getRegionInfoModel;
import static junit.framework.Assert.*;

public class ScannerFileImplTest {
    private final static String PATH = "src/main/resources/dbtxt/UA.txt";
    private final static String EMPTY_PATH = "";
    private final static String CYR_NAME = "Дніпропетровська область";
    private final static String LATIN_NAME = "Dnipropetrovska Oblast'";
    private final static String NON_CYRILLIC = "non cyrillic";
    private final static String ISO_LANG = "uk";

    private ScannerFileImpl scannerFile;

    @Before
    public void setUp()  {
        scannerFile = new ScannerFileImpl();
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_return_exception_scanner()   {
        //WHEN
        Scanner scanner = ScannerFileImpl.getScanner(EMPTY_PATH);
    }

    @Test
    public void should_return_scanner()  {
        //WHEN
        Scanner scanner = ScannerFileImpl.getScanner(PATH);
        //THEN
        assertNotNull(scanner);
    }

//    @Test
//    public void print()  {
//
//    }

    @Test// TODO: 2/17/17 impl test
    public void parseDbFiles()  {
        //GIVEN
        //WHEN
        //THEN
//        scannerFile.parseDbFiles();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shoud_return_exception_for_empty_collection_setCityUkrName()   {
        // TODO: 2/13/17 continue impl test
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
        fail("Method should throw IllegalArgumentException");
    }

    @Test
    public void should_set_ukr_city_into_model_setCityUkrName()  {
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
        // TODO: 2/13/17 continue impl test
        //WHEN
        ScannerFileImpl.setCityUkrName(Collections.singletonList(model), Collections.singletonList(aUkrModel));
        //THEN
        assertNotNull(model);
        assertNotSame(modelCityUkrName, model.getCityUkrName());
        assertEquals(UKR_NAME, model.getCityUkrName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_return_exception_for_empty_collection_setInterAndCyrRegion()  {
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
    public void setInterAndCyrRegion()  {
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
        final String REGION_ID = "04";
        final String REG_CYR_NAME = "Одеська область";
        final String REG_INT_NAME = "Odes'ka Oblast'";
        final String FEATURE_CODE = "ADM1";

        RegionInfo regionInfo = getRegionInfoModel(REGION_ID, GEO_CITY_INDEX, REG_CYR_NAME, REG_INT_NAME, FEATURE_CODE);
        Model model = getModel(modelCityIndex, modelCityUkrName, modelCityInterName, modelLatitude, modelLongitude,
                modelRegionId, modelRegionCyrName, modelRegionInterName, featureCode);

        assertNotNull(regionInfo);
        assertNotNull(model);
        //WHEN
        ScannerFileImpl.setInterAndCyrRegion(Collections.singletonList(model), Collections.singletonList(regionInfo));
        //THEN
        assertEquals(REG_CYR_NAME, model.getRegionCyrillicName());
        assertEquals(REG_INT_NAME, model.getRegionInternationalName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_return_exception_for_empty_collection_setCyrNameInRegion()  {
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
        fail("Method should throw IllegalArgumentException");
    }

    @Test
    public void should_return_region_with_new_cyr_name_region_setCyrNameInRegions()  {
        //GIVEN
        final int GEO_CITY_INDEX = 8458701;
        final String REGION_ID = "01";
        final String REG_CYR_NAME = "Одеська область";
        final String REG_INT_NAME = "Odes'ka Oblast'";
        final String FEATURE_CODE = "ADM1";

        AlternativeModel aModel = getAlternativeModel(GEO_CITY_INDEX, ISO_LANG, CYR_NAME);
        RegionInfo regionInfo = getRegionInfoModel(REGION_ID, GEO_CITY_INDEX, REG_CYR_NAME, REG_INT_NAME, FEATURE_CODE);
        assertNotNull(regionInfo);
        assertNotNull(aModel);
        //WHEN
        scannerFile.setCyrNameIntoRegions(Collections.singletonList(aModel), Collections.singletonList(regionInfo));
        //THEN
        assertNotNull(regionInfo);
        assertNotSame(REG_CYR_NAME, regionInfo.getRegionCyrillicName());
        assertEquals(CYR_NAME, regionInfo.getRegionCyrillicName());
        assertEquals(REGION_ID, regionInfo.getRegionId());
        assertEquals(REG_INT_NAME, regionInfo.getRegionInternationalName());
        assertEquals(FEATURE_CODE, regionInfo.getFeatureCode());
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_return_exception_for_empty_path_findAlternativeRegions()  {
        //GIVEN
        //WHEN
        ScannerFileImpl.findAlternativeRegions(EMPTY_PATH);
        //THEN
        fail("Method should throw IllegalArgumentException");
    }

    @Test
    public void should_return_alternativeRegions()  {
        //GIVEN
        final int EXPECTED_SIZE = 7;
        final int EXPECTED_MATCH_FOR_468196 = 2;
        final int EXPECTED_MATCH_FOR_471101 = 1;
        final int EXPECTED_MATCH_FOR_492094 = 1;
        final int EXPECTED_MATCH_FOR_8458701 = 3;
        final String ALT_DB_PATH = "src/main/resources/dbtxt/alternativeNamesTest.txt";
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
        assertEquals(aModelList.get(0).getCyrillicName(), EXPECTED_CITY_UKR_NAME);
        assertEquals(aModelList.get(1).getCyrillicName(), EXPECTED_CITY_UKR_NAME);
        assertEquals(aModelList.get(2).getCyrillicName(), EXPECTED_CITY_UKR_NAME1);
        assertEquals(aModelList.get(3).getCyrillicName(), EXPECTED_CITY_UKR_NAME2);
        assertEquals(aModelList.get(4).getCyrillicName(), EXPECTED_CITY_UKR_NAME3);
        assertEquals(aModelList.get(5).getCyrillicName(), EXPECTED_CITY_UKR_NAME4);
        assertEquals(aModelList.get(6).getCyrillicName(), EXPECTED_CITY_UKR_NAME5);
        assertEquals(count(aModelList, aModelList.get(0).getGeoNameId()), EXPECTED_MATCH_FOR_468196);
        assertEquals(count(aModelList, aModelList.get(2).getGeoNameId()), EXPECTED_MATCH_FOR_471101);
        assertEquals(count(aModelList, aModelList.get(3).getGeoNameId()), EXPECTED_MATCH_FOR_492094);
        assertEquals(count(aModelList, aModelList.get(4).getGeoNameId()), EXPECTED_MATCH_FOR_8458701);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_return_exception_if_path_empty_findRegions()   {
        //GIVEN
        //WHEN
        List<RegionInfo> foundRegions = scannerFile.findRegions(EMPTY_PATH);
        //THEN
        fail("Method should throw IllegalArgumentException");
    }

    @Test
    public void should_return_regions_with_feature_code_ADM1_findRegions()  {
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
    public void should_return_exception_if_string_empty_language_check()   {
        //GIVEN
        final String emptyString = "";
        //WHEN
        ScannerFileImpl.languageCheck(emptyString);
        //THEN
        fail("Method should throw IllegalArgumentException");
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_return_exception_if_string_null_language_check()   {
        //GIVEN
        final String nullString = null;
        //WHEN
        ScannerFileImpl.languageCheck(nullString);
        //THEN
        fail("Method should throw IllegalArgumentException");
    }

    @Test
    public void should_return_is_true_after_string_language_check()  {
        //GIVEN
        //WHEN
        boolean cyrName = ScannerFileImpl.languageCheck(CYR_NAME);
        boolean latinName = ScannerFileImpl.languageCheck(LATIN_NAME);
        //THEN
        assertNotNull(cyrName);
        assertNotNull(latinName);
        assertNotSame(cyrName, latinName);
        assertTrue(cyrName);
        assertNotSame(true, latinName);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_return_exception_for_empty_array_languageCheck()   {
        //GIVEN
        final String[] emptyArray = {};
        //WHEN
        String checkedWord = ScannerFileImpl.languageCheck(emptyArray);
        //THEN
        fail("Method should throw IllegalArgumentException");
    }

    @Test
    public void should_return_first_cyrillic_word_or_non_cyr_from_array_after_language_check()  {
        //GIVEN
        final String[] wordsArray = {LATIN_NAME, CYR_NAME};
        final String[] latinWord = {LATIN_NAME};
        final String[] emptyString = {""};
        //WHEN
        String checkedWord = ScannerFileImpl.languageCheck(wordsArray);
        String checkedLatinWord = ScannerFileImpl.languageCheck(latinWord);
        String checkedEmptyString = ScannerFileImpl.languageCheck(emptyString);
        //THEN
        assertNotNull(checkedWord);
        assertEquals(checkedWord, CYR_NAME);

        assertNotNull(checkedLatinWord);
        assertEquals(checkedLatinWord, NON_CYRILLIC);

        assertNotNull(checkedEmptyString);
        assertEquals(NON_CYRILLIC, checkedEmptyString);
    }

    @Test
    public void should_return_optimizedAlternativeNamesList()  {
        final String UKR_NAME = "Станція Славута Перша";
        final String UKR_NAME1 = "Зупиночний Пункт Нове Депо";
        final String NON_CYR = "non cyrillic";
        final long GEO_NAME_ID = 8458701;
        final long GEO_NAME_ID1 = 859659;
        final int DUPLICATE_COUNT = 5;
        final int DUPLICATE_COUNT1 = 3;
        final int EXPECTED_SIZE = 1;

        //GIVEN
        AlternativeModel aUkrModel = getAlternativeModel(GEO_NAME_ID, ISO_LANG, UKR_NAME);
        List<AlternativeModel> aUkrModelsList = makeDuplicate(aUkrModel, DUPLICATE_COUNT);
        AlternativeModel aNonUkrModel = getAlternativeModel(GEO_NAME_ID, ISO_LANG, NON_CYR);
        List<AlternativeModel> aNonUkrModelsList = makeDuplicate(aNonUkrModel, DUPLICATE_COUNT);

        AlternativeModel aUkrModel1 = getAlternativeModel(GEO_NAME_ID1, ISO_LANG, UKR_NAME1);
        List<AlternativeModel> aUkrModelsList1 = makeDuplicate(aUkrModel1, DUPLICATE_COUNT1);
        AlternativeModel aNonUkrModel1 = getAlternativeModel(GEO_NAME_ID1, ISO_LANG, NON_CYR);
        List<AlternativeModel> aNonUkrModelsList1 = makeDuplicate(aNonUkrModel1, DUPLICATE_COUNT);

        AlternativeModel aUkrModel2 = getAlternativeModel(GEO_NAME_ID, ISO_LANG, UKR_NAME);
        AlternativeModel aUkrModel3 = getAlternativeModel(GEO_NAME_ID1, ISO_LANG, UKR_NAME1);

        aUkrModelsList.addAll(aNonUkrModelsList);
        aUkrModelsList1.addAll(aNonUkrModelsList1);
        List<AlternativeModel> aModelList3 = Arrays.asList(aNonUkrModel, aUkrModel2, aUkrModel2, aUkrModel3, aNonUkrModel1);
        //WHEN
        Collection<AlternativeModel> aOptimizedList = ScannerFileImpl.getOptimizedAlternativeNamesList(aUkrModelsList);
        Collection<AlternativeModel> aOptimizedList1 = ScannerFileImpl.getOptimizedAlternativeNamesList(aUkrModelsList1);
        Collection<AlternativeModel> aOptimizedList2 = ScannerFileImpl.getOptimizedAlternativeNamesList(aModelList3);
        //THEN
        assertNotNull(aUkrModelsList);
        assertNotNull(aUkrModelsList1);
        assertNotNull(aModelList3);
        assertNotSame(aUkrModelsList, aUkrModelsList1);
        assertNotSame(aUkrModelsList, aModelList3);
        assertNotSame(aUkrModelsList1, aModelList3);
        assertEquals(DUPLICATE_COUNT + DUPLICATE_COUNT, aUkrModelsList.size());
        assertEquals(DUPLICATE_COUNT1 + DUPLICATE_COUNT, aUkrModelsList1.size());
        assertEquals(DUPLICATE_COUNT, aModelList3.size());
        assertNotNull(aOptimizedList);
        assertNotNull(aOptimizedList1);
        assertNotNull(aOptimizedList2);
        assertEquals(EXPECTED_SIZE, aOptimizedList.size());
        assertEquals(EXPECTED_SIZE, aOptimizedList1.size());
        assertEquals(EXPECTED_SIZE + EXPECTED_SIZE, aOptimizedList2.size());
        assertEquals(UKR_NAME, new ArrayList<>(aOptimizedList).get(0).getCyrillicName());
        assertEquals(UKR_NAME1, new ArrayList<>(aOptimizedList1).get(0).getCyrillicName());
        assertEquals(UKR_NAME, new ArrayList<>(aOptimizedList2).get(0).getCyrillicName());
        assertEquals(UKR_NAME1, new ArrayList<>(aOptimizedList2).get(1).getCyrillicName());
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