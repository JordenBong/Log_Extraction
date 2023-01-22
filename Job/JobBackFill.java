package com.example.assignmentfop;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class JobBackFill {
    public int[] countJobBackFill = new int[7];
    public String[] month = {"June", "July", "August", "September", "October", "November", "December"};

    //Method to count jobBackfill
    public void countBackFill() {
        try {
            Scanner inputStream = new Scanner(new FileInputStream("C:\\Users\\Yu Jia\\Desktop\\Assignment FOP\\extracted_log"));
            while (inputStream.hasNextLine()) {
                String[] array = inputStream.nextLine().split(" ");
                String[] time = array[0].split("");
                int m = Integer.parseInt(time[6].concat(time[7]));
                if (array[1].equals("sched/backfill:")) {
                    if (array[2].equals("_start_job:")) {
                        switch (m) {
                            case 6:
                                countJobBackFill[0]++;
                                break;
                            case 7:
                                countJobBackFill[1]++;
                                break;
                            case 8:
                                countJobBackFill[2]++;
                                break;
                            case 9:
                                countJobBackFill[3]++;
                                break;
                            case 10:
                                countJobBackFill[4]++;
                                break;
                            case 11:
                                countJobBackFill[5]++;
                                break;
                            case 12:
                                countJobBackFill[6]++;
                                break;
                        }

                    }
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
        }

    }
    //method to diaplsy table job backfill
    public void display(){
        System.out.println("Number of Jobs Created through Backfill every month from June to December: ");
        System.out.println( "+-------------------------------+");
        System.out.printf("%-12s%-12s", "Month", "Number of job backfill");
        System.out.println("\n" + "+-------------------------------+");
        int totalJobBackFill = 0;
        for (int i = 0; i < month.length; i++) {
            System.out.printf("%-12s%-12s", month[i], countJobBackFill[i]);
            System.out.println("");
            totalJobBackFill += countJobBackFill[i];
        }
        System.out.println( "+-------------------------------+");
        System.out.println("Total Number of Job Created: " + totalJobBackFill);
        System.out.println("");
    }



}
