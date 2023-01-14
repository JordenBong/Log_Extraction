import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class CatchMonth
{
    public static void main(String[] args)
    {
        File file = new File("D:/Java/Assignment 1002/src/extracted_log.txt");
        Scanner sc = new Scanner(System.in);

        try
        {
            Scanner scanner = new Scanner(file);
            System.out.print("Enter the month you want to see (Jun-Dec): ");
            String find = sc.next();

            // Read each line of the file and add it to the list
            ArrayList<String> lines = new ArrayList<>();
            while (scanner.hasNextLine())
                lines.add(scanner.nextLine());
            scanner.close();

            //Store every line by month
            ArrayList<String> Jun = new ArrayList<>();
            ArrayList<String> Jul = new ArrayList<>();
            ArrayList<String> Aug = new ArrayList<>();
            ArrayList<String> Sep = new ArrayList<>();
            ArrayList<String> Oct = new ArrayList<>();
            ArrayList<String> Nov = new ArrayList<>();
            ArrayList<String> Dec = new ArrayList<>();
            int cJun = 0, cJul = 0, cAug = 0, cSep = 0, cOct = 0, cNov = 0, cDec = 0;

            // Convert the list to an array
            String[] linesArray = lines.toArray(new String[lines.size()]);

            for (String line : linesArray)
            {
                //Catch the Month
                Pattern pattern = Pattern.compile("(\\W)2022(\\W)(\\d{2})(\\W)(.{15})(\\W) (.*)");
                Matcher matcher = pattern.matcher(line);
                matcher.find();
                int number = Integer.parseInt(matcher.group(3));

                switch (number)
                {
                    case 6 ->
                    {
                        Jun.add(line);
                        cJun++;
                    }
                    case 7 ->
                    {
                        Jul.add(line);
                        cJul++;
                    }
                    case 8 ->
                    {
                        Aug.add(line);
                        cAug++;
                    }
                    case 9 ->
                    {
                        Sep.add(line);
                        cSep++;
                    }
                    case 10 ->
                    {
                        Oct.add(line);
                        cOct++;
                    }
                    case 11 ->
                    {
                        Nov.add(line);
                        cNov++;
                    }
                    case 12 ->
                    {
                        Dec.add(line);
                        cDec++;
                    }
                }

            }

            String[] JunA = Jun.toArray(new String[cJun]);
            String[] JulA = Jul.toArray(new String[cJul]);
            String[] AugA = Aug.toArray(new String[cAug]);
            String[] SepA = Sep.toArray(new String[cSep]);
            String[] OctA = Oct.toArray(new String[cOct]);
            String[] NovA = Nov.toArray(new String[cNov]);
            String[] DecA = Dec.toArray(new String[cDec]);

            switch (find)
            {
                case "Jun" ->
                {
                    for (String lineJ : JunA)
                    {
                        System.out.println(lineJ);
                    }
                    System.out.println("Total line in " + find + ": " + cJun);
                }
                case "Jul" ->
                {
                    for (String lineJ : JulA)
                    {
                        System.out.println(lineJ);
                    }
                    System.out.println("Total line in " + find + ": " + cJul);
                }
                case "Aug" ->
                {
                    for (String lineJ : AugA)
                    {
                        System.out.println(lineJ);
                    }
                    System.out.println("Total line in " + find + ": " + cAug);
                }
                case "Sep" ->
                {
                    for (String lineJ : SepA)
                    {
                        System.out.println(lineJ);
                    }
                    System.out.println("Total line in " + find + ": " + cSep);
                }
                case "Oct" ->
                {
                    for (String lineJ : OctA)
                    {
                        System.out.println(lineJ);
                    }
                    System.out.println("Total line in " + find + ": " + cOct);
                }
                case "Nov" ->
                {
                    for (String lineJ : NovA)
                    {
                        System.out.println(lineJ);
                    }
                    System.out.println("Total line in " + find + ": " + cNov);
                }
                case "Dec" ->
                {
                    for (String lineJ : DecA)
                    {
                        System.out.println(lineJ);
                    }
                    System.out.println("Total line in " + find + ": " + cDec);
                }
            }

            System.out.println("Total Line: " + (cJun+cJul+cAug+cSep+cOct+cNov+cDec));
        }

        catch (FileNotFoundException e)
        {
            System.out.println("File not found: " + file);
        }
    }
}

