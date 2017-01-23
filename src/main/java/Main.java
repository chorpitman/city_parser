import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String path = "/Users/olegchorpita/Documents/WORKSPACE/LUNA_PROJECTS/src/main/resources/UA.txt";
        File filePath = new File(path);
        String cityUkrNamePattern = "([А-яіг]+(?:[^,][А-яіг]+)?)";
        String cityEngNamePattern = "[abc]";
        String cityIndexPattern = "";
        String latitudePattern = "";
        String longitudePattern = "";

        Scanner scanner = new Scanner(filePath);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] splitLine = line.split("\\t");
            for (int i = 0; i < splitLine.length; i++) {
                String s = splitLine[i];
                if (s.matches(cityUkrNamePattern)) {
                    System.out.println(s);
                } else {
                    System.out.println("her");
                }
            }
        }
    }
}

