package com.lunapps.sevice.impl;

import com.lunapps.model.AlternativeModel;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static com.lunapps.config.utils.BaseTestAltModelHelper.getAlternativeModel;
import static com.lunapps.config.utils.BaseTestAltModelHelper.makeDuplicate;
import static junit.framework.Assert.*;

public class ScannerFileImplTest {
    private final static String PATH = "src/main/resources/dbtxt/UA.txt";
    private final String EMPTY_PATH = "";

    private ScannerFileImpl scannerFile;


    @Before
    public void setUp() throws Exception {
        scannerFile = new ScannerFileImpl();
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_return_exception_scanner() throws Exception {
        //WHEN
        Scanner scanner = ScannerFileImpl.getScanner(EMPTY_PATH);
    }

    @Test
    public void should_return_scanner() throws Exception {
        //WHEN
        Scanner scanner = ScannerFileImpl.getScanner(PATH);
        //THEN
        assertNotNull(scanner);
    }


    //    @Test
    public void parseDbFiles() throws Exception {

    }

    //    @Test
    public void findExistCyr() throws Exception {

    }

    //    @Test
    public void setCityUkrName() throws Exception {

    }

    //    @Test
    public void setInterCyrRegion() throws Exception {

    }

    //    @Test
    public void setCyrNameInRegions() throws Exception {

    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_findAlternativeRegions() throws Exception {
        //GIVEN
        //WHEN
        scannerFile.findAlternativeRegions(EMPTY_PATH);
    }

    @Test
    public void should_return_alternativeRegions() throws Exception {
        //GIVEN
        final int EXPECTED_SIZE = 7;
        final int EXPECTED_MATCH_FOR_468196 = 2;
        final int EXPECTED_MATCH_FOR_471101 = 1;
        final int EXPECTED_MATCH_FOR_492094 = 1;
        final int EXPECTED_MATCH_FOR_8458701 = 3;
        final String ALT_DB_PATH = "src/main/resources/dbtxt/alterbativeNamesTest.txt";
        final String EXPECTED_CITY_UKR_NAME = "Катеринівка";
        final String EXPECTED_CITY_UKR_NAME1 = "Всеволожськ";
        final String EXPECTED_CITY_UKR_NAME2 = "Слов'янськ на Кубані";
        final String EXPECTED_CITY_UKR_NAME3 = "Станція Славута Перша";
        final String EXPECTED_CITY_UKR_NAME4 = "Станція Славута I";
        final String EXPECTED_CITY_UKR_NAME5 = "Славута I";
        //WHEN
        List<AlternativeModel> aModelList = scannerFile.findAlternativeRegions(ALT_DB_PATH);
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

    //    @Test
    public void findRegions() throws Exception {

    }


    //    @Test
    public void languageCheckString() throws Exception {

    }

    //    @Test
    public void languageCheckArrays() throws Exception {

    }


    //    @Test
    public void print() throws Exception {

    }

    @Test
    public void should_return_optimizedAlternativeNamesList() throws Exception {
        final String UKR_NAME = "Станція Славута Перша";
        final String UKR_NAME1 = "Зупиночний Пункт Нове Депо";
        final String NON_CYR = "non cyrillic";
        final long GEO_NAME_ID = 8458701;
        final long GEO_NAME_ID1 = 859659;
        final String ISO_LANG = "uk";
        final int DUPLICATE_COUNT = 5;
        final int DUPLICATE_COUNT1 = 3;
        final int EXPEXTED_SIZE = 1;

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
        assertEquals(aUkrModelsList.size(), DUPLICATE_COUNT + DUPLICATE_COUNT);
        assertEquals(aUkrModelsList1.size(), DUPLICATE_COUNT1 + DUPLICATE_COUNT);
        assertEquals(aModelList3.size(), DUPLICATE_COUNT);
        assertNotNull(aOptimizedList);
        assertNotNull(aOptimizedList1);
        assertNotNull(aOptimizedList2);
        assertEquals(aOptimizedList.size(), EXPEXTED_SIZE);
        assertEquals(aOptimizedList1.size(), EXPEXTED_SIZE);
        assertEquals(aOptimizedList2.size(), EXPEXTED_SIZE + EXPEXTED_SIZE);
        assertEquals(new ArrayList<>(aOptimizedList).get(0).getCyrillicName(), UKR_NAME);
        assertEquals(new ArrayList<>(aOptimizedList1).get(0).getCyrillicName(), UKR_NAME1);
        assertEquals(new ArrayList<>(aOptimizedList2).get(0).getCyrillicName(), UKR_NAME);
        assertEquals(new ArrayList<>(aOptimizedList2).get(1).getCyrillicName(), UKR_NAME1);
    }

    //UTILS METHODS
    public int count(List<AlternativeModel> list, long id) {
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