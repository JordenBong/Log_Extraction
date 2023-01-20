package com.example.assignmentfop;

import java.io.FileInputStream;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class JobEnded {
    //initialize varialble
    public int[] countJobCompleted = new int[7];
    public String[] month = {"June", "July", "August", "September", "October", "November", "December"};

    //Method to count Job Ended
    public void countJobEnded() {
        try {
            Scanner inputStream = new Scanner(new FileInputStream("C:\\Users\\Yu Jia\\Desktop\\Assignment FOP\\extracted_log"));
            while (inputStream.hasNextLine()) {
                String[] array = inputStream.nextLine().split(" ");
                String[] time = array[0].split("");
                int m = Integer.parseInt(time[6].concat(time[7]));
                if (array[1].equals("_job_complete:")) {
                    if (array[3].equals("done")) {
                        switch (m) {
                            case 6:
                                countJobCompleted[0]++;
                                break;
                            case 7:
                                countJobCompleted[1]++;
                                break;
                            case 8:
                                countJobCompleted[2]++;
                                break;
                            case 9:
                                countJobCompleted[3]++;
                                break;
                            case 10:
                                countJobCompleted[4]++;
                                break;
                            case 11:
                                countJobCompleted[5]++;
                                break;
                            case 12:
                                countJobCompleted[6]++;
                                break;
                        }
                    }
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
        }

    }
    //Method to print table of job complete
    public void display(){
        System.out.println("Number of jobs ended every month from June to December:");
        System.out.println( "+------------------------------+");
        System.out.printf("%-12s%-12s", "Month", "Number of job ended");
        System.out.println("\n" + "+------------------------------+");
        int totalJobComplete = 0;
        for (int i = 0; i < month.length; i++) {
            System.out.printf("%-12s%-12s", month[i], countJobCompleted[i]);
            System.out.println("");
            totalJobComplete += countJobCompleted[i];
        }
        System.out.println("+------------------------------+");
        System.out.println("Total Number of Job Completed: " + totalJobComplete);
    }

}

