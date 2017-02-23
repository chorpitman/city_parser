package com.lunapps.sevice.impl;

import com.lunapps.configuration.AppConfig;
import com.lunapps.model.Model;
import com.lunapps.repository.ModelRepository;
import com.lunapps.utils.Utils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class TestDuplicates {

    @Test
    public void should_return_model_list_of_duplicates() {
        //GIVEN
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ModelRepository cityDao = context.getBean(ModelRepository.class);
        List<Model> duplicatesList = cityDao.findAll();
        //SORT
        List<Model> qwe = sortModelByParam(duplicatesList);
        System.out.println("duplicatesList " + qwe.size());
        //WHEN
        List<Model> list = Utils.getListWithDuplicatesCoordinates(qwe);
        System.out.println(list.size());
        cityDao.save(list);
        //THEN
        Assert.assertEquals(6, list.size());
    }

    private List<Model> sortModelByParam(List<Model> duplicatesList) {
        List<Model> qwe = new LinkedList<>(duplicatesList);
        Collections.sort(qwe, Comparator.comparing(Model::getRegionCyrillicName)
                .thenComparing(Model::getCityUkrName)
                .thenComparing(Model::getLatitude));
        return qwe;
    }
}
