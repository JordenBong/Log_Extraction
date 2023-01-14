
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        // File to be read
        File file = new File("D:/Java/Assignment 1002/src/extracted_log.txt");

        try {
            // Create a scanner for the file
            Scanner scanner = new Scanner(file);

            // Read each line of the file and add it to the list
            ArrayList<String> lines = new ArrayList<String>();
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }

            // Close the scanner
            scanner.close();
            ArrayList<String> Jun = new ArrayList<String>();
            String[] June = new String[10000];
            // Convert the list to an array
            String[] linesArray = lines.toArray(new String[lines.size()]);

            //Print the contents of the array
//            System.out.println("Outside: " + matcher.group(3));
            for (String line : linesArray) {
                Pattern pattern = Pattern.compile("(\\W)2022(\\W)(\\d{2})(\\W)(.{15})(\\W) (.*)");
                Matcher matcher = pattern.matcher(line);
                matcher.find();
                int number = Integer.parseInt(matcher.group(3));
                if (number == 6) {
                    Jun.add(line);
                    System.out.println(Jun);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + file.toString());
        }
    }
}