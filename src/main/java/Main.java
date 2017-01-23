import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String path = "/Users/olegchorpita/Documents/WORKSPACE/LUNA_PROJECTS/src/main/resources/UA.txt";
        File filePath = new File(path);
        String cityUkrNamePattern = "([А-яіг]+(?:[^,][А-яіг]+)?)\\s";
        String cityEngNamePattern = "-|\\n";
        String cityIndexPattern = "";
        String latitudePattern = "";
        String longitudePattern = "";

        Scanner scanner = new Scanner(filePath);

        ArrayList<Model> models = new ArrayList<Model>();

        while (scanner.hasNext()) {
            System.out.println(scanner.hasNext(Pattern.compile(cityEngNamePattern)));
        }
    }
}
