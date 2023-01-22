package Assignment;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class WexitStatus {
    
    //Pattern to extrace "Kill Job" data
    Pattern wexitstatus = Pattern.compile("\\[(.*?)T(.*?)\\] _job_complete: JobId=(\\d+) WEXITSTATUS (\\d+)");
    
    //Array List to store killed job
    private ArrayList <String> date = new ArrayList <String>();
    private ArrayList <String> time = new ArrayList <String>();
    private ArrayList <String> jobID = new ArrayList <String>();
    private ArrayList <String> exit = new ArrayList <String>();
    
    //Hashset to get all wexitstatus
    HashSet <String> status = new HashSet <String>();
    //Convert hashset to arraylist
    ArrayList <String> status11 = new ArrayList <String>(status);
    
    //Variable to store count
    private int count = 0;
    
    //Array to store grouping data
    String [] status1 = new String[status.size()];
    int [] total = new int[status.size()];
    
    //Object declaration
    BufferedReader reader;
    BufferedReader reader1;
    
    
    //Method to extract info to store into arraylist for further calculation
    public void extractInfo(){
        
        try{
            reader = new BufferedReader (new FileReader (Launch.fileSource));
            String input = reader.readLine();
            
           while(input != null){
               
                Matcher m = wexitstatus.matcher(input);

                if (m.find()){
                    //extarct info into arraylist
                    date.add(m.group(1));
                    time.add(m.group(2));
                    jobID.add(m.group(3));
                    exit.add(m.group(4));
                    
                    //extract wexitstatus and put into hashset
                    status.add(m.group(4));
                    
                    count++;
                }
                
                input = reader.readLine();
            }
        }
        
        catch(IOException e){
            e.printStackTrace();
        } 
    }
    
    //Method to display the table and show the result of total exit status
    public void display(){
        System.out.println("Table of WexitStatus ");
        System.out.println();
        System.out.printf("%-15s %-20s %-15s %-15s \n","Date", "Time", "Job ID", "Wexitstatus");
        System.out.println("+----------------------------------------------------------------------------+");
        for(int i=0; i<jobID.size(); i++){
            System.out.printf("%-15s %-20s %-15s %-15s \n",date.get(i), time.get(i), jobID.get(i), exit.get(i));
        }
        
        System.out.println();
        System.out.println("Total Backfill: " + count);
    }
    
    public ArrayList getList(){
        status11 = new ArrayList <String>(status);
        return status11;
    }
    
    
    
}
