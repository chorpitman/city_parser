package com.lunapps.utils;

import com.lunapps.configuration.HibernateConfig;
import com.lunapps.model.Model;
import com.lunapps.repository.ModelRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateConfig.class})
@TestPropertySource(value = "classpath:app-config-test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Sql(scripts = {"classpath:sql/ddl.sql", "classpath:sql/insert_dnipro.sql"})
public class UtilsTest {

    @Autowired
    private ModelRepository repository;

    @Test
    public void should_return_count_non_cyr_entity_countNonCyrillicModel() {
        //GIVEN
        final int EXPECTED_COUNT = 2;
        List<Model> all = repository.findAll();

        //WHEN
        int countNonCyrillicModel = Utils.countNonCyrillicModel(all);

        //THEN
        assertNotNull(countNonCyrillicModel);
        assertEquals(EXPECTED_COUNT, countNonCyrillicModel);
    }

    @Test
    public void should_return_model_with_non_cyr_city_name_getNonCyrCityNameModels() {
        //GIVEN
        final int EXPECTED_COUNT = 2;
        List<Model> modelsList = repository.findAll();

        //WHEN
        Collection<Model> nonCyrCityNameModels = Utils.getNonCyrCityNameModels(modelsList);

        //THEN
        assertNotNull(modelsList);
        assertNotNull(nonCyrCityNameModels);
        assertEquals(EXPECTED_COUNT, nonCyrCityNameModels.size());
    }

    @Test
    public void should_remove_with_non_cyr_city_name_removeNonCyrCityNameModels() {
        //GIVEN
        final int EXPECTED_COUNT = 11;
        final int EXPECTED_COUNT_AFTER_REMOVE_NON_CYR = 9;

        List<Model> modelsList = repository.findAll();
        ArrayList<Model> copyModelsList = new ArrayList<>(modelsList);

        //WHEN
        Utils.removeNonCyrCityNameModels(modelsList);

        //THEN
        assertNotNull(modelsList);
        assertNotNull(copyModelsList);
        assertNotSame(copyModelsList, modelsList);
        assertEquals(EXPECTED_COUNT, copyModelsList.size());
        assertEquals(EXPECTED_COUNT_AFTER_REMOVE_NON_CYR, modelsList.size());
    }

    @Test
    public void should_return_model_list_of_duplicates_coordinates() {
        //GIVEN
        final int EXPECTED_COUNT = 7;
        List<Model> modelList = repository.findAll();
        List<Model> sortedDuplicatesList = Utils.sortModelByRegCityLat(modelList);

        //WHEN
        List<Model> foundListWithDuplicates = Utils.getModelsWithDuplicatesCoordinates(sortedDuplicatesList);

        //THEN
        assertNotNull(modelList);
        assertNotNull(sortedDuplicatesList);
        assertNotNull(foundListWithDuplicates);
        assertNotSame(modelList, sortedDuplicatesList);
        assertNotSame(sortedDuplicatesList, foundListWithDuplicates);
        assertEquals(EXPECTED_COUNT, foundListWithDuplicates.size());
    }

    @Test
    public void should_sort_by_reg_city_lat_sortModelByRegCityLat() {
        //GIVEN
        List<Model> modelList = repository.findAll();
        List<Model> copyModelList = new ArrayList<>(modelList);

        //WHEN
        Utils.sortModelByRegCityLat(modelList);
        //THEN
        assertNotNull(modelList);
        assertNotNull(copyModelList);
        assertNotSame(modelList, copyModelList);
    }

    @Test
    public void shold_return_distance_between_2_point_getDistanceBetween2Points() {
        //GIVEN
        List<Model> modelList = repository.findAll();
        Double lat = modelList.get(0).getLatitude();
        Double lon = modelList.get(0).getLongitude();
        Double lat1 = modelList.get(1).getLatitude();
        Double lon1 = modelList.get(1).getLongitude();

        //WHEN
        Double distanceBetween2Points = Utils.getDistanceBetween2Points(lat, lon, lat1, lon1);
        //THEN
        assertNotNull(modelList);
        assertNotNull(lat);
        assertNotNull(lon);
        assertNotNull(lat1);
        assertNotNull(lon1);
        assertTrue(distanceBetween2Points > 169);
    }
}