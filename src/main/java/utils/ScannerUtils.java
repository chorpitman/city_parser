package utils;

import model.Model;

import java.util.List;

public interface ScannerUtils {
    List<Model> scanPath(String filepath);

    void print(List<Model> modelList);
}
