package Assignment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class JobCreated
{
    //initialize countJobAllocate and month
    public int[] countJobAllocate = new int[7];
    public String[] month = {"June", "July", "August", "September", "October", "November", "December"};

    //Method to calculate the total number of job allocate and return a countJobCreated array
    public int[] countJobCreated()
    {
        try
        {
            Scanner inputStream = new Scanner(new FileInputStream(Launch.fileSource));
            while (inputStream.hasNextLine())
            {
                String[] array = inputStream.nextLine().split(" ");
                String[] time = array[0].split("");
                int m = Integer.parseInt(time[6].concat(time[7]));

                if (array.length > 3 && array[1].concat(array[2]).equals("sched:Allocate"))
                {
                    switch (m)
                    {
                        case 6:
                            countJobAllocate[0]++;
                            break;
                        case 7:
                            countJobAllocate[1]++;
                            break;
                        case 8:
                            countJobAllocate[2]++;
                            break;
                        case 9:
                            countJobAllocate[3]++;
                            break;
                        case 10:
                            countJobAllocate[4]++;
                            break;
                        case 11:
                            countJobAllocate[5]++;
                            break;
                        case 12:
                            countJobAllocate[6]++;
                            break;
                    }
                }

            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File was not found");
        }
        return countJobAllocate;
    }

    //Method to print the table of Job Created
    public void display()
    {
        System.out.println("Number of Jobs Created through Allocate every month from June to December: ");
        System.out.println("+-------------------------------+");
        System.out.printf("%-12s%-12s", "Month", "Number of Job Created");
        System.out.println("\n" + "+-------------------------------+");
        //calculate total job allocate
        int totalJobAllocate = 0;
        for (int i = 0; i < month.length; i++)
        {
            System.out.printf("%-12s%-12s", month[i], countJobAllocate[i]);
            System.out.println("");
            totalJobAllocate += countJobAllocate[i];
        }
        //calculate average
        int sum=0;
        for(int i=0;i<6;i++)
            sum+=countJobAllocate[i];

        int average = sum / 6;
        System.out.println("+-------------------------------+");
        System.out.println("Total Number of Job Created: " + totalJobAllocate);
        System.out.println("Average Number of Job Created every month: " + average);
        System.out.println("");
    }

}




