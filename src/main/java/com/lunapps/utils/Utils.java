package com.lunapps.utils;

import com.lunapps.model.Model;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class Utils {
    private static final String NAME_NON_CYR = "non cyrillic";

    public static int countNonCyrillic(Collection<Model> models) {
        if (CollectionUtils.isEmpty(models)) throw new IllegalArgumentException("Collection can not be empty or null");

        int count = 0;
        if (CollectionUtils.isNotEmpty(models)) {
            for (Model entity : models) {
                String name = entity.getCityUkrName();
                if (Objects.equals(name, NAME_NON_CYR)) {
                    count++;
                }
            }
        }
        return count;
    }

    public static Collection<Model> returnListModelWithNonCyrCityName(Collection<Model> modelList) {
        if (CollectionUtils.isEmpty(modelList))
            throw new IllegalArgumentException("modelList can not be null or empty");

        ArrayList<Model> nonCyrList = new ArrayList<>();
        for (Model model : modelList) {
            if (model.getCityUkrName().equals("non cyrillic")) {
                nonCyrList.add(model);
            }
        }
        return nonCyrList;
    }

    public static void removeEntityWithNonCyrCityName(Collection<Model> models) {
        if (CollectionUtils.isEmpty(models)) throw new IllegalArgumentException("models can not be empty or null");
        models.removeIf(model -> model.getCityUkrName().equals(NAME_NON_CYR));

    }
}
