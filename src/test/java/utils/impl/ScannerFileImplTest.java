package utils.impl;

import model.Model;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ScannerFileImplTest {
    private final static String EMPTY_FILE_PATH = "src/main/resources/UA_empty.txt";
    final String FILE_PATH = "src/main/resources/UA.txt";

    ScannerFileImpl utils;

    @Before
    public void setUp() throws Exception {
        utils = new ScannerFileImpl();
    }

    @Test
    public void should_return_empty_list() throws Exception {
        //WHEN
        List<Model> models = utils.scanPath(EMPTY_FILE_PATH);
        //THEN
        assertNotNull(models);
        assertTrue(models.isEmpty());
    }
}