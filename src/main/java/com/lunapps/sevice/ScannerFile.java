package com.lunapps.sevice;

import com.lunapps.model.Model;

import java.util.Collection;

public interface ScannerFile {
    Collection<Model> parseDbFiles(String ukrDbPath, String alterNameDb);

    void print(Collection<?> coll);
}
