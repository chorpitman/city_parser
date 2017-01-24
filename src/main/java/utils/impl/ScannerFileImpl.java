package utils.impl;

import model.Model;
import org.apache.commons.collections.CollectionUtils;
import utils.ScannerFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScannerFileImpl implements ScannerFile {
    private final static String TABULATION = "\\t";
    private final static String COMA = ",";
    private final static int CITY_INDEX = 0;
    private final static int INTERNATIONAL_NAME = 2;
    private final static int NAME = 3;
    private final static int LATITUDE = 4;
    private final static int LONGITUDE = 5;
    private final static int REGION_ID = 10;

    public List<Model> scanPath(String filepath) {
        File file = new File(filepath);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Filepath or file does not exist");
        }
        List<Model> models = new ArrayList<Model>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] splitLine = line.split(TABULATION);

            Model model = new Model();

            model.setCityIndex(Integer.parseInt(splitLine[CITY_INDEX]));
            model.setInternationalName((splitLine[INTERNATIONAL_NAME]));

            String stringLine = splitLine[NAME];
            String[] splintedStrings = stringLine.split(COMA);
            model.setName(splintedStrings[splintedStrings.length - 1]);
            model.setLatitude(Double.parseDouble(splitLine[LATITUDE]));
            model.setLongitude(Double.parseDouble(splitLine[LONGITUDE]));
            model.setRegionId(splitLine[REGION_ID]);
            models.add(model);
        }
        scanner.close();
        return models;
    }

    public void print(List<Model> modelList) {
        CollectionUtils.isEmpty(modelList);
        for (Model model : modelList) {
            System.out.println(model);
        }
    }
}
