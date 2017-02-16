package com.lunapps.sevice;

import com.lunapps.model.Model;

import java.util.Collection;

public interface GooglePlacesSearch {
    Collection<Model> nearbySearch(Collection<Model> nonCyrList, String language);
}
