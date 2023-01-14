import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CatchKeyword
{
    public static void main(String[] args)
    {
        File file = new File("D:/Java/Assignment 1002/src/extracted_log.txt");

        try
        {
            Scanner scanner = new Scanner(file);

            // Read each line of the file and add it to the list
            ArrayList<String> lines = new ArrayList<String>();
            while (scanner.hasNextLine())
                lines.add(scanner.nextLine());
            scanner.close();
            String[] linesArray = lines.toArray(new String[lines.size()]);

            //Find no.of job created
            int cJobCreated = 0;
            for (String line : linesArray)
            {
                Pattern pattern = Pattern.compile("sched:");
                Matcher matcher = pattern.matcher(line);
                if (matcher.find())
                    cJobCreated++;
            }

            //Find no.of job done/ended
            int cJobEnd = 0;
            for (String line : linesArray)
            {
                Pattern pattern = Pattern.compile("_job_complete: JobId=(\\d{5}) done");
                Matcher matcher = pattern.matcher(line);
                if (matcher.find())
                    cJobEnd++;
            }

            //Find no.of job killed
            int cJobKilled = 0;
            ArrayList<String> jobKilledID = new ArrayList<String>();
            for (String line : linesArray)
            {
                Pattern pattern = Pattern.compile("REQUEST_KILL_JOB JobId=(\\d{5})");
                Matcher matcher = pattern.matcher(line);
                if (matcher.find())
                {
                    jobKilledID.add(matcher.group(1));
                    cJobKilled++;
                }
            }

            System.out.println("Total number of job done: " + cJobCreated);
            System.out.println("Total number of job ended: " + cJobEnd);
            System.out.println("Total number of job killed: " + cJobKilled);
            System.out.println("Killed Job ID: " + jobKilledID);

        }

        catch (FileNotFoundException e)
        {
            System.out.println("File not found: " + file.toString());
        }
    }
}



