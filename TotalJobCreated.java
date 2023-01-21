package com.example.assignmentfop;

public class TotalJobCreated {
    //print total job created table
    public void display(String [] month,int []countJobAllocate,int []countJobBackFill){
     System.out.println("Number of Total Jobs Created every month from June to December: ");
        System.out.println("+-------------------------------+");
        System.out.printf("%-12s%-12s", "Month", "Number of Job Created");
        System.out.println("\n" + "+-------------------------------+");
    //calculate total job allocate
    int totalJobCreated = 0;
        for (int i = 0; i < month.length; i++) {
        System.out.printf("%-12s%-12s", month[i], countJobAllocate[i]+countJobBackFill[i]);
        System.out.println("");
        totalJobCreated += countJobAllocate[i];
        totalJobCreated += countJobBackFill[i];
    }
    //calculate average by sum the month from june to november only
    int sum=0;
    for(int i=0;i<6;i++){
       sum+=countJobAllocate[i]+countJobBackFill[i];
    }
    int average = sum / 6;
    System.out.println("+-------------------------------+");
    System.out.println("Total Number of Job Created: " + totalJobCreated);
    System.out.println("Average Number of Job Created every month: " + average);
    System.out.println("");
    }
}