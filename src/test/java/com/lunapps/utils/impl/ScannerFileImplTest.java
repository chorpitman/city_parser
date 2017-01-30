package com.lunapps.utils.impl;

import com.lunapps.model.Model;
import com.lunapps.model.RegionInfo;
import com.lunapps.sevice.impl.ScannerFileImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ScannerFileImplTest {
    private final static String COMMA = ",";

    private ScannerFileImpl utils;

    @Before
    public void setUp() throws Exception {
        utils = new ScannerFileImpl();
    }

    @Test
    public void should_return_empty_list() throws Exception {
        //GIVEN
        final String EMPTY_FILE_PATH = "src/main/resources/UA_empty.txt";
        //WHEN
        List<Model> models = utils.scanPath(EMPTY_FILE_PATH);
        //THEN
        assertNotNull(models);
        assertTrue(models.isEmpty());
    }

    @Test
    public void should_return_region_info() throws Exception {
        //GIVEN
        String FILE_PATH_TEST = "src/main/resources/UA_test.txt";
        //WHEN
        List<RegionInfo> regionCodes = ScannerFileImpl.findRegionCodes(FILE_PATH_TEST);
        //THEN
        assertEquals("04", regionCodes.get(0).getRegionId());
        assertEquals("Дніпропетровська область", regionCodes.get(0).getRegionCyrillicName());
        assertEquals("Dnipropetrovska Oblast'", regionCodes.get(0).getRegionInternationalName());
//
    }

    @Test
    public void should_return_false_for_non_cyrillic_chars() throws Exception {
        //GIVEN
        final String CHINESE = "xxx已下架xxx";
        final String korean = "드니프로페트로우시크";
        final String chinese = "第聂伯罗彼得罗夫斯克";
        final String japanise = "ドニプロペトロウシク";
        final String georgian = "დნეპროპეტროვსკი";
        final String tai = "ดนีโปรเปตรอฟสค์";
        final String hindi = "द्नेप्रोपेत्रोव्स्क";
        final String ukr = "Дніпропетровська область";
        final String ukr1 = "DNK,Dnepr,Dnepropetrovsk,Dnepropetrovska,Dnepropetrowsk,Dnetropetrovsk,Dniepropetrovsk,Dniepropetrovskas,Dniepropetrowsk,Dnipro,Dnipropetrovs'k,Dnipropetrovsk,Dnipropetrovsko,Dnipropetrovs’k,Dnipropetrowsk,Dnjepropetrovsk,Dnjepropetrowsk,Dnjipropetrovsk,Dnyipropetrovszk,Dněpropetrovsk,Dņepropetrovska,Ekaterinoslav,Ekaṭerinoslav,Gorad Dneprapjatrousk,Iekaterinoslav,Katerynoslav,Ntnipropetrofsk,Yekaterinoslav,Yekaterinovslav,d ni por pet rxfskh,deunipeulopeteulousikeu,di nie bo luo bi de luo fu si ke,dnepropetrovska,dnybrwbtrwfsk,dnyprwptrwbsq,dnyprwptrwfsk,dnyprwptrwwsk,donipuropetoroushiku,Ντνιπροπετρόφσκ,Горад Днепрапятроўск,Днепр,Днепропетровск,Дніпро,Дніпропетровськ,Днїпропетровск,Дњепропетровск,Екатеринослав,Դնեպրոպետրովսկ,דניפרופטרובסק,دنيبروبتروفسك,دنیپروپتروفسک,دنیپروپترووسک,द्नेप्रोपेत्रोव्स्क,ดนีโปรเปตรอฟสค์,დნეპროპეტროვსკი,ドニプロペトロウシク,第聂伯罗彼得罗夫斯克,드니프로페트로우시크";
        final String ukr2 = "Vodokhranilishche,Dniprodzerzhyns'ke Vodoskhovyshche,Dniprodzerzhyns’ke Vodoskhovyshche";
        final String ukr3 = "Dnepr-Donbass,Kanal Dnipro-Donbas";
        //WHEN
        //THEN
        assertEquals(false, containsHanScriptStream(CHINESE, COMMA));
        assertEquals(false, containsHanScriptStream(korean, COMMA));
        assertEquals(false, containsHanScriptStream(chinese, COMMA));
        assertEquals(false, containsHanScriptStream(japanise, COMMA));
        assertEquals(false, containsHanScriptStream(georgian, COMMA));
        assertEquals(false, containsHanScriptStream(tai, COMMA));
        assertEquals(false, containsHanScriptStream(hindi, COMMA));
        assertEquals(true, containsHanScriptStream(ukr, COMMA));

        assertEquals(true, containsHanScriptStream(ukr1, COMMA));
        assertEquals(false, containsHanScriptStream(ukr2, COMMA));
        assertEquals(false, containsHanScriptStream(ukr3, COMMA));
    }

    private static boolean containsHanScriptStream(String s, String split) {
        String[] splittedLine = s.split(split);
        String INFO = "";
        for (int i = 0; i < splittedLine.length; i++) {
            if (splittedLine[i].codePoints().anyMatch(
                    c -> Character.UnicodeScript.of(c) == Character.UnicodeScript.CYRILLIC)) return true;
        }
        return false;
    }

    // TODO: 1/26/17 remove redundant method
    public static boolean containsHanScript(String s) {
        for (int i = 0; i < s.length(); ) {
            int codepoint = s.codePointAt(i);
            i += Character.charCount(codepoint);
            if (Character.UnicodeScript.of(codepoint) == Character.UnicodeScript.CYRILLIC) {
                return true;
            }
        }
        return false;
    }
}