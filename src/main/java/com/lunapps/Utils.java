package com.lunapps;

import com.lunapps.model.Model;
import com.lunapps.sevice.Transliterator;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class Utils {

    public static int countNonCyrillic(Collection<Model> models) {
        if (CollectionUtils.isEmpty(models)) throw new IllegalArgumentException("Collection can not be empty or null");

        final String EMPTY_STR = "non cyrillic";
        int count = 0;
        if (!CollectionUtils.isEmpty(models)) {
            for (Model entity : models) {
                String name = entity.getName();
                if (Objects.equals(name, EMPTY_STR)) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void transliterate(Collection<Model> models) {
        if (CollectionUtils.isEmpty(models)) throw new IllegalArgumentException("Collection can not be empty or null");

        final String EMPTY_STR = "non cyrillic";
        for (Model entity : models) {
            String name = entity.getName();
            if (Objects.equals(name, EMPTY_STR)) {
                System.out.println(entity.getInternationalName());
                String s = Transliterator.lat2cyr(entity.getInternationalName());
                System.out.println(s);
                entity.setName(s);
                System.out.println();
            }
        }
    }
}
