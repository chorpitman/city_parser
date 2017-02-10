package com.lunapps.sevice.impl;

import com.lunapps.model.AlternativeModel;
import com.lunapps.model.RegionInfo;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ScannerFileImplTestRegionsOfUkraine {
    private ScannerFileImpl scannerFile;

    @Before
    public void setUp() throws Exception {
        scannerFile = new ScannerFileImpl();
    }

    @Test
    public void should_return_empty_list_of_the_Ukraine_areas() {
        final int ACTUAL_SIZE = 0;
        //GIVEN
        final String FILE_PATH = "src/main/resources/dbtxt/UA_empty.txt";
        //WHEN
        List<RegionInfo> regionCodes = scannerFile.findRegions(FILE_PATH);
        //THEN
        assertEquals(regionCodes.size(), ACTUAL_SIZE);
        assertNotNull(regionCodes);
    }

    @Test
    public void should_return_one_name_of_the_Ukraine_areas() {
        //GIVEN
        final String FILE_PATH = "src/main/resources/dbtxt/UA_test=one_area.txt";
        final String NAME = "Дніпропетровська область";
        final String INT_NAME = "Dnipropetrovska Oblast'";
        final String REGION_ID = "04";
        final int ACTUAL_SIZE = 1;
        final int CITY_INDEX = 709929;
        //WHEN
        List<RegionInfo> regionCodes = scannerFile.findRegions(FILE_PATH);
        //THEN
        assertEquals(regionCodes.size(), ACTUAL_SIZE);
        assertEquals(regionCodes.get(0).getRegionCyrillicName(), NAME);
        assertEquals(regionCodes.get(0).getRegionId(), REGION_ID);
        assertEquals(regionCodes.get(0).getCityIndex(), CITY_INDEX);
        assertEquals(regionCodes.get(0).getRegionInternationalName(), INT_NAME);
    }

    @Test
    public void should_return_correct_ukr_name_of_the_Ukraine_areas() {
        //GIVEN
        final String FILE_PATH = "src/main/resources/dbtxt/UA_test=Ukr_regions.txt";

        final String ZHITOMIR = "Житомирська область";
        final String ZAPOR = "Запорізька область";
        final String ZAKARPATTYA = "Закарпатська Область";
        final String VOLYNSKA = "Волинська Область";
        final String VINNITSA = "Вінницька Область";
        final String TERNOPIL = "Тернопільська область";
        final String SUMMY = "Сумська область";
        final String SEVASTOPOL = "Місто Севастополь";
        final String RIVNO = "Рівненська область";
        final String POLTAVA = "Полтавська область";
        final String ODESA = "Одеська область";
        final String MYKOLAYV = "Миколаївська область";
        final String LVIV = "Львівська область";
        final String LUGANSK = "Луганська область";
        final String KYIV = "Київська область";
        final String KYIV_MISTO = "Місто Київ";
        final String KRYM = "Республіка Крим";
        final String KYROVOGRAD = "Кіровоградська область";
        final String KHMELNITSK = "Хмельницька область";
        final String KHERSON = "Херсонська область";
        final String KHARKIV = "Харківська область";
        final String FRANKIV = "Івано-Франківська область";
        final String DONETSK = "Донецька область";
        final String DNIPRO = "Дніпропетровська область";
        final String CHERNIVTSY = "Чернівецька область";
        final String CHERNIGYV = "Чернігівська область";
        final String CHERKASY = "Черкаська область";

        //WHEN
        List<RegionInfo> regionCodes = scannerFile.findRegions(FILE_PATH);
        //THEN
        assertEquals(CHERKASY, searchRegion(regionCodes, CHERKASY));
        assertEquals(CHERNIGYV, searchRegion(regionCodes, CHERNIGYV));
        assertEquals(CHERNIVTSY, searchRegion(regionCodes, CHERNIVTSY));
        assertEquals(DNIPRO, searchRegion(regionCodes, DNIPRO));
        assertEquals(DONETSK, searchRegion(regionCodes, DONETSK));
        assertEquals(KHERSON, searchRegion(regionCodes, KHERSON));
        assertEquals(KHMELNITSK, searchRegion(regionCodes, KHMELNITSK));
        assertEquals(KYROVOGRAD, searchRegion(regionCodes, KYROVOGRAD));
        assertEquals(KRYM, searchRegion(regionCodes, KRYM));
        assertEquals(KYIV_MISTO, searchRegion(regionCodes, KYIV_MISTO));
        assertEquals(LUGANSK, searchRegion(regionCodes, LUGANSK));
        assertEquals(LVIV, searchRegion(regionCodes, LVIV));
        assertEquals(ODESA, searchRegion(regionCodes, ODESA));
        assertEquals(POLTAVA, searchRegion(regionCodes, POLTAVA));
        assertEquals(RIVNO, searchRegion(regionCodes, RIVNO));
        assertEquals(SEVASTOPOL, searchRegion(regionCodes, SEVASTOPOL));
        assertEquals(SUMMY, searchRegion(regionCodes, SUMMY));
        assertEquals(TERNOPIL, searchRegion(regionCodes, TERNOPIL));
        assertEquals(VINNITSA, searchRegion(regionCodes, VINNITSA));
        assertEquals(ZAKARPATTYA, searchRegion(regionCodes, ZAKARPATTYA));
        assertEquals(ZAPOR, searchRegion(regionCodes, ZAPOR));
        assertEquals(ZHITOMIR, searchRegion(regionCodes, ZHITOMIR));
        // TODO: 2/3/17 need to fix
//        assertEquals(KYIV, searchRegion(regionCodes, KYIV));
//        assertEquals(MYKOLAYV, searchRegion(regionCodes, MYKOLAYV));
//        assertEquals(VOLYNSKA, searchRegion(regionCodes, VOLYNSKA));
//        assertEquals(FRANKIV, searchRegion(regionCodes, FRANKIV));
//        assertEquals(KHARKIV, searchRegion(regionCodes, KHARKIV));

    }

    @Test
    public void experimentWithMatchers() {
        final String text1 = "Київська область";
        final String text2 = "Кіеўская вобласць";

        String pattern = "йїі";
        Pattern patternString = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = patternString.matcher(text1);
        System.out.println(matcher.matches());

    }


    @Test
    public void should_return_name_of_the_Ukraine_areas() {
        final int ACTUAL_SIZE = 27;
        final String MESSAGE_INFO = "region list can not be empty";
        final String MESSAGE_INFO_SIZE = "region list size can not be empty";
        //GIVEN
        final String PREPARED_DB = "src/main/resources/dbtxt/UA_test=Ukr_regions.txt";
        final String FULL_DB = "src/main/resources/dbtxt/UA_test=Ukr_regions.txt";
        //WHEN
        List<RegionInfo> preparedRegionDB = scannerFile.findRegions(PREPARED_DB);
        List<RegionInfo> actualRegionDb = scannerFile.findRegions(FULL_DB);
        //THEN
        assertNotNull(MESSAGE_INFO);
        assertNotNull(MESSAGE_INFO, actualRegionDb);
        assertEquals(MESSAGE_INFO_SIZE, preparedRegionDB.size(), ACTUAL_SIZE);
        assertEquals(MESSAGE_INFO_SIZE, actualRegionDb.size(), ACTUAL_SIZE);

        assertEquals(preparedRegionDB.get(0).getRegionId(), actualRegionDb.get(0).getRegionId());
        assertEquals(preparedRegionDB.get(0).getCityIndex(), actualRegionDb.get(0).getCityIndex());
        assertEquals(preparedRegionDB.get(0).getRegionInternationalName(), actualRegionDb.get(0).getRegionInternationalName());
        assertEquals(preparedRegionDB.get(0).getRegionCyrillicName(), actualRegionDb.get(0).getRegionCyrillicName());

        assertEquals(preparedRegionDB.get(1).getRegionInternationalName(), actualRegionDb.get(1).getRegionInternationalName());
        assertEquals(preparedRegionDB.get(1).getRegionId(), actualRegionDb.get(1).getRegionId());
        assertEquals(preparedRegionDB.get(1).getCityIndex(), actualRegionDb.get(1).getCityIndex());
        assertEquals(preparedRegionDB.get(1).getRegionCyrillicName(), actualRegionDb.get(1).getRegionCyrillicName());

        assertEquals(preparedRegionDB.get(2).getRegionId(), actualRegionDb.get(2).getRegionId());
        assertEquals(preparedRegionDB.get(2).getCityIndex(), actualRegionDb.get(2).getCityIndex());
        assertEquals(preparedRegionDB.get(2).getRegionInternationalName(), actualRegionDb.get(2).getRegionInternationalName());
        assertEquals(preparedRegionDB.get(2).getRegionCyrillicName(), actualRegionDb.get(2).getRegionCyrillicName());

        assertEquals(preparedRegionDB.get(3).getRegionId(), actualRegionDb.get(3).getRegionId());
        assertEquals(preparedRegionDB.get(3).getCityIndex(), actualRegionDb.get(3).getCityIndex());
        assertEquals(preparedRegionDB.get(3).getRegionInternationalName(), actualRegionDb.get(3).getRegionInternationalName());
        assertEquals(preparedRegionDB.get(3).getRegionCyrillicName(), actualRegionDb.get(3).getRegionCyrillicName());

        assertEquals(preparedRegionDB.get(3).getRegionId(), actualRegionDb.get(3).getRegionId());
        assertEquals(preparedRegionDB.get(3).getCityIndex(), actualRegionDb.get(3).getCityIndex());
        assertEquals(preparedRegionDB.get(3).getRegionInternationalName(), actualRegionDb.get(3).getRegionInternationalName());
        assertEquals(preparedRegionDB.get(3).getRegionCyrillicName(), actualRegionDb.get(3).getRegionCyrillicName());

        assertEquals(preparedRegionDB.get(4).getRegionId(), actualRegionDb.get(4).getRegionId());
        assertEquals(preparedRegionDB.get(4).getCityIndex(), actualRegionDb.get(4).getCityIndex());
        assertEquals(preparedRegionDB.get(4).getRegionInternationalName(), actualRegionDb.get(4).getRegionInternationalName());
        assertEquals(preparedRegionDB.get(4).getRegionCyrillicName(), actualRegionDb.get(4).getRegionCyrillicName());

        assertEquals(preparedRegionDB.get(5).getRegionId(), actualRegionDb.get(5).getRegionId());
        assertEquals(preparedRegionDB.get(5).getCityIndex(), actualRegionDb.get(5).getCityIndex());
        assertEquals(preparedRegionDB.get(5).getRegionInternationalName(), actualRegionDb.get(5).getRegionInternationalName());
        assertEquals(preparedRegionDB.get(5).getRegionCyrillicName(), actualRegionDb.get(5).getRegionCyrillicName());

        assertEquals(preparedRegionDB.get(6).getRegionId(), actualRegionDb.get(6).getRegionId());
        assertEquals(preparedRegionDB.get(6).getCityIndex(), actualRegionDb.get(6).getCityIndex());
        assertEquals(preparedRegionDB.get(6).getRegionInternationalName(), actualRegionDb.get(6).getRegionInternationalName());
        assertEquals(preparedRegionDB.get(6).getRegionCyrillicName(), actualRegionDb.get(6).getRegionCyrillicName());

        assertEquals(preparedRegionDB.get(7).getRegionId(), actualRegionDb.get(7).getRegionId());
        assertEquals(preparedRegionDB.get(7).getCityIndex(), actualRegionDb.get(7).getCityIndex());
        assertEquals(preparedRegionDB.get(7).getRegionInternationalName(), actualRegionDb.get(7).getRegionInternationalName());
        assertEquals(preparedRegionDB.get(7).getRegionCyrillicName(), actualRegionDb.get(7).getRegionCyrillicName());

        assertEquals(preparedRegionDB.get(8).getRegionId(), actualRegionDb.get(8).getRegionId());
        assertEquals(preparedRegionDB.get(8).getCityIndex(), actualRegionDb.get(8).getCityIndex());
        assertEquals(preparedRegionDB.get(8).getRegionInternationalName(), actualRegionDb.get(8).getRegionInternationalName());
        assertEquals(preparedRegionDB.get(8).getRegionCyrillicName(), actualRegionDb.get(8).getRegionCyrillicName());

        assertEquals(preparedRegionDB.get(9).getRegionId(), actualRegionDb.get(9).getRegionId());
        assertEquals(preparedRegionDB.get(9).getCityIndex(), actualRegionDb.get(9).getCityIndex());
        assertEquals(preparedRegionDB.get(9).getRegionInternationalName(), actualRegionDb.get(9).getRegionInternationalName());
        assertEquals(preparedRegionDB.get(9).getRegionCyrillicName(), actualRegionDb.get(9).getRegionCyrillicName());

        assertEquals(preparedRegionDB.get(10).getRegionId(), actualRegionDb.get(10).getRegionId());
        assertEquals(preparedRegionDB.get(10).getCityIndex(), actualRegionDb.get(10).getCityIndex());
        assertEquals(preparedRegionDB.get(10).getRegionInternationalName(), actualRegionDb.get(10).getRegionInternationalName());
        assertEquals(preparedRegionDB.get(10).getRegionCyrillicName(), actualRegionDb.get(10).getRegionCyrillicName());

        assertEquals(preparedRegionDB.get(11).getRegionId(), actualRegionDb.get(11).getRegionId());
        assertEquals(preparedRegionDB.get(11).getCityIndex(), actualRegionDb.get(11).getCityIndex());
        assertEquals(preparedRegionDB.get(11).getRegionInternationalName(), actualRegionDb.get(11).getRegionInternationalName());
        assertEquals(preparedRegionDB.get(11).getRegionCyrillicName(), actualRegionDb.get(11).getRegionCyrillicName());

        assertEquals(preparedRegionDB.get(12).getRegionId(), actualRegionDb.get(12).getRegionId());
        assertEquals(preparedRegionDB.get(12).getCityIndex(), actualRegionDb.get(12).getCityIndex());
        assertEquals(preparedRegionDB.get(12).getRegionInternationalName(), actualRegionDb.get(12).getRegionInternationalName());
        assertEquals(preparedRegionDB.get(12).getRegionCyrillicName(), actualRegionDb.get(12).getRegionCyrillicName());

        assertEquals(preparedRegionDB.get(13).getRegionId(), actualRegionDb.get(13).getRegionId());
        assertEquals(preparedRegionDB.get(13).getCityIndex(), actualRegionDb.get(13).getCityIndex());
        assertEquals(preparedRegionDB.get(13).getRegionInternationalName(), actualRegionDb.get(13).getRegionInternationalName());
        assertEquals(preparedRegionDB.get(13).getRegionCyrillicName(), actualRegionDb.get(13).getRegionCyrillicName());

        assertEquals(preparedRegionDB.get(14).getRegionId(), actualRegionDb.get(14).getRegionId());
        assertEquals(preparedRegionDB.get(14).getCityIndex(), actualRegionDb.get(14).getCityIndex());
        assertEquals(preparedRegionDB.get(14).getRegionInternationalName(), actualRegionDb.get(14).getRegionInternationalName());
        assertEquals(preparedRegionDB.get(14).getRegionCyrillicName(), actualRegionDb.get(14).getRegionCyrillicName());

        assertEquals(preparedRegionDB.get(15).getRegionId(), actualRegionDb.get(15).getRegionId());
        assertEquals(preparedRegionDB.get(15).getCityIndex(), actualRegionDb.get(15).getCityIndex());
        assertEquals(preparedRegionDB.get(15).getRegionInternationalName(), actualRegionDb.get(15).getRegionInternationalName());
        assertEquals(preparedRegionDB.get(15).getRegionCyrillicName(), actualRegionDb.get(15).getRegionCyrillicName());

        assertEquals(preparedRegionDB.get(15).getRegionId(), actualRegionDb.get(15).getRegionId());
        assertEquals(preparedRegionDB.get(15).getCityIndex(), actualRegionDb.get(15).getCityIndex());
        assertEquals(preparedRegionDB.get(15).getRegionInternationalName(), actualRegionDb.get(15).getRegionInternationalName());
        assertEquals(preparedRegionDB.get(15).getRegionCyrillicName(), actualRegionDb.get(15).getRegionCyrillicName());

        assertEquals(preparedRegionDB.get(15).getRegionId(), actualRegionDb.get(15).getRegionId());
        assertEquals(preparedRegionDB.get(15).getCityIndex(), actualRegionDb.get(15).getCityIndex());
        assertEquals(preparedRegionDB.get(15).getRegionInternationalName(), actualRegionDb.get(15).getRegionInternationalName());
        assertEquals(preparedRegionDB.get(15).getRegionCyrillicName(), actualRegionDb.get(15).getRegionCyrillicName());

        assertEquals(preparedRegionDB.get(16).getRegionId(), actualRegionDb.get(16).getRegionId());
        assertEquals(preparedRegionDB.get(16).getCityIndex(), actualRegionDb.get(16).getCityIndex());
        assertEquals(preparedRegionDB.get(16).getRegionInternationalName(), actualRegionDb.get(16).getRegionInternationalName());
        assertEquals(preparedRegionDB.get(16).getRegionCyrillicName(), actualRegionDb.get(16).getRegionCyrillicName());

        assertEquals(preparedRegionDB.get(17).getRegionId(), actualRegionDb.get(17).getRegionId());
        assertEquals(preparedRegionDB.get(17).getCityIndex(), actualRegionDb.get(17).getCityIndex());
        assertEquals(preparedRegionDB.get(17).getRegionInternationalName(), actualRegionDb.get(17).getRegionInternationalName());
        assertEquals(preparedRegionDB.get(17).getRegionCyrillicName(), actualRegionDb.get(17).getRegionCyrillicName());

        assertEquals(preparedRegionDB.get(18).getRegionId(), actualRegionDb.get(18).getRegionId());
        assertEquals(preparedRegionDB.get(18).getCityIndex(), actualRegionDb.get(18).getCityIndex());
        assertEquals(preparedRegionDB.get(18).getRegionInternationalName(), actualRegionDb.get(18).getRegionInternationalName());
        assertEquals(preparedRegionDB.get(18).getRegionCyrillicName(), actualRegionDb.get(18).getRegionCyrillicName());

        assertEquals(preparedRegionDB.get(19).getRegionId(), actualRegionDb.get(19).getRegionId());
        assertEquals(preparedRegionDB.get(19).getCityIndex(), actualRegionDb.get(19).getCityIndex());
        assertEquals(preparedRegionDB.get(19).getRegionInternationalName(), actualRegionDb.get(19).getRegionInternationalName());
        assertEquals(preparedRegionDB.get(19).getRegionCyrillicName(), actualRegionDb.get(19).getRegionCyrillicName());

        assertEquals(preparedRegionDB.get(20).getRegionId(), actualRegionDb.get(20).getRegionId());
        assertEquals(preparedRegionDB.get(20).getCityIndex(), actualRegionDb.get(20).getCityIndex());
        assertEquals(preparedRegionDB.get(20).getRegionInternationalName(), actualRegionDb.get(20).getRegionInternationalName());
        assertEquals(preparedRegionDB.get(20).getRegionCyrillicName(), actualRegionDb.get(20).getRegionCyrillicName());

        assertEquals(preparedRegionDB.get(21).getRegionId(), actualRegionDb.get(21).getRegionId());
        assertEquals(preparedRegionDB.get(21).getCityIndex(), actualRegionDb.get(21).getCityIndex());
        assertEquals(preparedRegionDB.get(21).getRegionInternationalName(), actualRegionDb.get(21).getRegionInternationalName());
        assertEquals(preparedRegionDB.get(21).getRegionCyrillicName(), actualRegionDb.get(21).getRegionCyrillicName());

        assertEquals(preparedRegionDB.get(21).getRegionId(), actualRegionDb.get(21).getRegionId());
        assertEquals(preparedRegionDB.get(21).getCityIndex(), actualRegionDb.get(21).getCityIndex());
        assertEquals(preparedRegionDB.get(21).getRegionInternationalName(), actualRegionDb.get(21).getRegionInternationalName());
        assertEquals(preparedRegionDB.get(21).getRegionCyrillicName(), actualRegionDb.get(21).getRegionCyrillicName());

        assertEquals(preparedRegionDB.get(22).getRegionId(), actualRegionDb.get(22).getRegionId());
        assertEquals(preparedRegionDB.get(22).getCityIndex(), actualRegionDb.get(22).getCityIndex());
        assertEquals(preparedRegionDB.get(22).getRegionInternationalName(), actualRegionDb.get(22).getRegionInternationalName());
        assertEquals(preparedRegionDB.get(22).getRegionCyrillicName(), actualRegionDb.get(22).getRegionCyrillicName());

        assertEquals(preparedRegionDB.get(23).getRegionId(), actualRegionDb.get(23).getRegionId());
        assertEquals(preparedRegionDB.get(23).getCityIndex(), actualRegionDb.get(23).getCityIndex());
        assertEquals(preparedRegionDB.get(23).getRegionInternationalName(), actualRegionDb.get(23).getRegionInternationalName());
        assertEquals(preparedRegionDB.get(23).getRegionCyrillicName(), actualRegionDb.get(23).getRegionCyrillicName());

        assertEquals(preparedRegionDB.get(24).getRegionId(), actualRegionDb.get(24).getRegionId());
        assertEquals(preparedRegionDB.get(24).getCityIndex(), actualRegionDb.get(24).getCityIndex());
        assertEquals(preparedRegionDB.get(24).getRegionInternationalName(), actualRegionDb.get(24).getRegionInternationalName());
        assertEquals(preparedRegionDB.get(24).getRegionCyrillicName(), actualRegionDb.get(24).getRegionCyrillicName());

        assertEquals(preparedRegionDB.get(25).getRegionId(), actualRegionDb.get(25).getRegionId());
        assertEquals(preparedRegionDB.get(25).getCityIndex(), actualRegionDb.get(25).getCityIndex());
        assertEquals(preparedRegionDB.get(25).getRegionInternationalName(), actualRegionDb.get(25).getRegionInternationalName());
        assertEquals(preparedRegionDB.get(25).getRegionCyrillicName(), actualRegionDb.get(25).getRegionCyrillicName());

        assertEquals(preparedRegionDB.get(26).getRegionId(), actualRegionDb.get(26).getRegionId());
        assertEquals(preparedRegionDB.get(26).getCityIndex(), actualRegionDb.get(26).getCityIndex());
        assertEquals(preparedRegionDB.get(26).getRegionInternationalName(), actualRegionDb.get(26).getRegionInternationalName());
        assertEquals(preparedRegionDB.get(26).getRegionCyrillicName(), actualRegionDb.get(26).getRegionCyrillicName());
    }

    private String searchRegion(List<RegionInfo> regionInfos, String searchedRegion) {
        if (CollectionUtils.isEmpty(regionInfos)) throw new IllegalArgumentException("Regions info is empty");
        String foundRegion = "";
        for (RegionInfo regionInfo : regionInfos) {
            if (Objects.equals(regionInfo.getRegionCyrillicName(), searchedRegion)) {
                foundRegion = regionInfo.getRegionCyrillicName();
            }
        }
        return foundRegion;
    }
}