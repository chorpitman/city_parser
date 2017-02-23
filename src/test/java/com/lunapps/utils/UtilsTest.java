package com.lunapps.utils;

import com.lunapps.config.utils.BaseTestModelHelper;
import com.lunapps.configuration.AppConfig;
import com.lunapps.model.Model;
import com.lunapps.repository.ModelRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static junit.framework.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

public class UtilsTest {
    private ModelRepository repository;

    @Before
    public void setUp() throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        repository = context.getBean(ModelRepository.class);
    }

    @Test
    public void should_return_count_non_cyr_entity_countNonCyrillic()  {
        final String NON_CYR_NAME = "non cyrillic";
        final String CYR_NAME = "Львівська область";
        final int EXPECTED_SIZE = 2;
        final int EXPECTED_COUNT = 1;
        //GIVEN
        Model non_cyr = BaseTestModelHelper.getModel(NON_CYR_NAME);
        Model cyr = BaseTestModelHelper.getModel(CYR_NAME);
        ArrayList<Model> list = new ArrayList<>();
        list.addAll(Arrays.asList(non_cyr, cyr));
        //WHEN
        int count = Utils.countNonCyrillic(list);
        //THEN
        assertNotNull(non_cyr);
        assertNotNull(cyr);
        assertEquals(list.size(), EXPECTED_SIZE);
        assertEquals(count, EXPECTED_COUNT);
    }

    @Test
    public void createModelWithNonCyrCityName()  {
        final String NON_CYR_NAME = "non cyrillic";
        final String CYR_NAME = "Львівська область";
        final int EXPECTED_COUNT = 1;
        //GIVEN
        Model non_cyr = BaseTestModelHelper.getModel(NON_CYR_NAME);
        Model cyr = BaseTestModelHelper.getModel(CYR_NAME);
        ArrayList<Model> list = new ArrayList<>();
        list.addAll(Arrays.asList(non_cyr, cyr));
        //WHEN
        Collection<Model> listModelWithNonCyrCityName = Utils.returnListModelWithNonCyrCityName(list);
        //THEN
        assertNotNull(cyr);
        assertNotNull(non_cyr);
        assertNotSame(list, listModelWithNonCyrCityName);
        assertEquals(EXPECTED_COUNT, listModelWithNonCyrCityName.size());
        assertTrue(listModelWithNonCyrCityName.contains(non_cyr));
    }

    @Test
    public void should_remove_with_non_cyr_city_name_EntityWithNonCyrCityName()  {
        final String NON_CYR_NAME = "non cyrillic";
        final String CYR_NAME = "Львівська область";
        final int EXPECTED_COUNT = 1;
        //GIVEN
        Model non_cyr = BaseTestModelHelper.getModel(NON_CYR_NAME);
        Model cyr = BaseTestModelHelper.getModel(CYR_NAME);
        ArrayList<Model> list = new ArrayList<>();
        list.addAll(Arrays.asList(non_cyr, cyr));
        //WHEN
        Utils.removeEntityWithNonCyrCityName(list);
        //THEN
        assertNotNull(non_cyr);
        assertNotNull(cyr);
        assertEquals(list.size(), EXPECTED_COUNT);
        assertEquals(list.get(0).getCityUkrName(), CYR_NAME);
        assertEquals(list.get(0).getId(), cyr.getId());
    }

    @Test
    public void should_return_model_list_of_duplicates() {
        //GIVEN
        List<Model> duplicatesList = repository.findAll();
        List<Model> sortedDuplicatesList = Utils.sortModelByRegCityLat(duplicatesList);
        //WHEN
        List<Model> foundListWithDuplicates = Utils.getListWithDuplicatesCoordinates(sortedDuplicatesList);
        //THEN
        assertNotNull(duplicatesList);
        assertNotNull(sortedDuplicatesList);
        assertNotNull(foundListWithDuplicates);
        assertNotSame(duplicatesList, sortedDuplicatesList);
        assertNotSame(sortedDuplicatesList, foundListWithDuplicates);
        assertEquals(6, foundListWithDuplicates.size());
    }
}