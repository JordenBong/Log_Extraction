import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
public class ReadPrintFile
{
    public static void main(String[] args)
    {
        //Location to read log file
        String fileOri = "D:/Java/Assignment 1002/src/extracted_log.txt";

        Scanner sc =  new Scanner(System.in);
        StringBuilder content = new StringBuilder();
        int line = 0;

        try(BufferedReader br = new BufferedReader(new FileReader(fileOri)))
        {
            //Scanner inFile1 = new Scanner(new FileReader(fileOri)).useDelimiter(" \\s*");
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null)
            {
                content.append(sCurrentLine).append("\n");
                line ++;
            }

        }

        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        catch (IOException E)
        {
            System.out.println("Error reading file");
        }

        String fileContent = content.toString();
        System.out.println(fileContent);

        System.out.println("Total Line: " + line);
    }
}