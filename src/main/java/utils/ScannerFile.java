package utils;

import model.Model;

import java.util.List;

public interface ScannerFile {
    List<Model> scanPath(String filepath);

    void print(List<?> modelList);
}
