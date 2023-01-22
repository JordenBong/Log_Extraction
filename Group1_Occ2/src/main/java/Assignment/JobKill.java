package Assignment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class JobKill {
    public String[] m = {"June", "July", "August", "September", "October", "November", "December"};
    public int[] countJobKill = new int[7];

    //Method to count jobkill
    public void countKill() {
        try {
            Scanner inputStream = new Scanner(new FileInputStream(Launch.fileSource));
            while (inputStream.hasNextLine()) {
                String[] array = inputStream.nextLine().split(" ");
                String[] time = array[0].split("");
                int m = Integer.parseInt(time[6].concat(time[7]));
                if (array[1].equals("_slurm_rpc_kill_job:"))
                    if (array[2].equals("REQUEST_KILL_JOB"))
                switch (m) {
                    case 6:
                        countJobKill[0]++;
                        break;
                    case 7:
                        countJobKill[1]++;
                        break;
                    case 8:
                        countJobKill[2]++;
                        break;
                    case 9:
                        countJobKill[3]++;
                        break;
                    case 10:
                        countJobKill[4]++;
                        break;
                    case 11:
                        countJobKill[5]++;
                        break;
                    case 12:
                        countJobKill[6]++;
                        break;
                }

            }

        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
        }

    }
    //method to print jobkill table
    public void display(){
        System.out.println("Number of Job Kill every month from June to December: ");
        System.out.println("+-------------------------------+");
        System.out.printf("%-12s%-12s", "Month", "Number of Job Kill");
        System.out.println("\n" + "+-------------------------------+");
        int totalJobKill = 0;
        for (int i = 0; i < m.length; i++) {
            System.out.printf("%-12s%10s", m[i], countJobKill[i]);
            System.out.println("");
            totalJobKill += countJobKill[i];
        }
        System.out.println("+-------------------------------+");
        System.out.println("Total Number of Job Kill: " + totalJobKill);
        System.out.println("");
    }
}

