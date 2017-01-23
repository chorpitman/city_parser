package utils.impl;

import model.Model;
import org.apache.commons.collections.CollectionUtils;
import utils.ScannerUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScannerUtilsImpl implements ScannerUtils {
    private final static String TABULATION = "\\t";
    private final static String COMA = ",";

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

            for (int i = 0; i < splitLine.length; i++) {
                if (i == 0) {
                    model.setCityIndex(Integer.parseInt(splitLine[i]));
                }
                // TODO: 23.01.2017 think how to resolve fields name with  = ""
                if (i == 2) {
                    model.setInternationalName(splitLine[i]);
                }

                if (i == 3) {
                    String stringLine = splitLine[i];
                    String[] splintedStrings = stringLine.split(COMA);
                    model.setName(splintedStrings[splintedStrings.length - 1]);
                }

                if (i == 4) {
                    model.setLatitude(Double.parseDouble(splitLine[i]));
                }

                if (i == 5) {
                    model.setLongitude(Double.parseDouble(splitLine[i]));
                }
            }
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
