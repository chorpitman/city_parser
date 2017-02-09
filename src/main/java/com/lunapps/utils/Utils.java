package com.lunapps.utils;

import com.lunapps.model.Model;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collection;
import java.util.Objects;

public class Utils {
    private static final String NAME_NON_CYR = "non cyrillic";

    public static int countNonCyrillic(Collection<Model> models) {
        if (CollectionUtils.isEmpty(models)) throw new IllegalArgumentException("Collection can not be empty or null");

        int count = 0;
        if (!CollectionUtils.isEmpty(models)) {
            for (Model entity : models) {
                String name = entity.getCityUkrName();
                if (Objects.equals(name, NAME_NON_CYR)) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void transliterate(Collection<Model> models) {
        if (CollectionUtils.isEmpty(models)) throw new IllegalArgumentException("Collection can not be empty or null");

        for (Model entity : models) {
            String name = entity.getCityUkrName();
            if (Objects.equals(name, NAME_NON_CYR)) {
                System.out.println(entity.getInternationalName());
                String s = Transliterator.lat2cyr(entity.getInternationalName());
                System.out.println(s);
                entity.setCityUkrName(s);
                System.out.println();
            }
        }
    }
}
