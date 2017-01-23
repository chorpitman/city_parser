import model.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        final String PATH = "/Users/olegchorpita/Documents/WORKSPACE/LUNA_PROJECTS/src/main/resources/UA.txt";
        File filePath = new File(PATH);
        Scanner scanner = new Scanner(filePath);
        List<Model> models = new ArrayList<Model>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] splitLine = line.split("\\t");
            Model model = new Model();

            for (int i = 0; i < splitLine.length; i++) {
                if (i == 0) {
                    model.setCityIndex(Integer.parseInt(splitLine[i]));
                }

                if (i == 2) {
                    model.setInternationalName(splitLine[i]);
                }

                if (i == 3) {
                    String stringLine = splitLine[i];
                    String[] splintedStrings = stringLine.split(",");
                    model.setName(splintedStrings[splintedStrings.length - 1]);
                }

                if (i == 4) {
                    model.setLatitude(Double.parseDouble(splitLine[i]));
                }

                if (i == 5) {
                    model.setLongtitude(Double.parseDouble(splitLine[i]));
                }
            }
            scanner.close();
            models.add(model);
        }
        printToConsole(models);
    }

    private static void printToConsole(List<Model> models) {
        for (Model model : models) {
            System.out.println(model);
        }
    }
}

