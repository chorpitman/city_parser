package com.lunapps;

import com.lunapps.model.Model;
import com.lunapps.sevice.Transliterator;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * Created by olegchorpita on 2/2/17.
 */
public class Utils {

    public static void countNonCyrillic(List<Model> models) {
        final String EMPTY_STR = "non cyrillic";
        int count = 0;
        if (!CollectionUtils.isEmpty(models)) {
            for (Model entity : models) {
                String name = entity.getName();
                if (name == EMPTY_STR) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    public static void transliterate(List<Model> models) {
        final String EMPTY_STR = "non cyrillic";
        CollectionUtils.isEmpty(models);
        for (Model entity : models) {
            String name = entity.getName();
            if (name == EMPTY_STR) {
                System.out.println(entity.getInternationalName());
                String s = Transliterator.lat2cyr(entity.getInternationalName());
                System.out.println(s);
                entity.setName(s);
                System.out.println();
            }
        }
    }
}
