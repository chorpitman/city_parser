package com.lunapps.sevice;

import com.lunapps.model.Model;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service("scanFileService")
public interface ScannerFile {
    Collection<Model> parseDbFiles(String ukrDbPath, String alterNameDb);

    void print(Collection<?> coll);
}
