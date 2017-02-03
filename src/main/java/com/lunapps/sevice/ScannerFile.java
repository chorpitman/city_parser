package com.lunapps.sevice;

import com.lunapps.model.Model;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("scanFileService")
public interface ScannerFile {
    List<Model> scanPath(String filepath);

    void print(List<?> modelList);
}
