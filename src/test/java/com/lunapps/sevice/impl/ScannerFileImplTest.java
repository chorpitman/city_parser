package com.lunapps.sevice.impl;

import com.lunapps.model.AlternativeModel;
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

    @Test
    public void findRegions() throws Exception {

    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_findAlternativeRegions() throws Exception {
        //GIVEN

        //WHEN
        scannerFile.findAlternativeRegions(EMPTY_PATH);
    }

//    @Test
    public void findAlternativeRegions() throws Exception {

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
    public void getOptimizedAlternativeNamesList() throws Exception {
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
}