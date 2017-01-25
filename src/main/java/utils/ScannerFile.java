package utils;

import model.Model;
import model.ModelTest;

import java.util.List;

public interface ScannerFile {
    List<Model> scanPath(String filepath);

    void print(List<?> modelList);

    List<?> scanPathTest(String filepath);
}
