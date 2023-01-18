import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Backfill {
    
    //Pattern to extrace "Kill Job" data
    Pattern backfill = Pattern.compile("\\[(.*?)T(.*?)\\] sched/backfill: _start_job: Started JobId=(\\d+) in (cpu|gpu)-(\\w+) on (cpu|gpu)(.*)");
    
    //Array List to store killed job
    private ArrayList <String> date = new ArrayList <String>();
    private ArrayList <String> time = new ArrayList <String>();
    private ArrayList <String> jobID = new ArrayList <String>();
    private ArrayList <String> cpuType = new ArrayList <String>();
    private ArrayList <String> cpuNum = new ArrayList <String>();
    
    //Variable to store count
    private int count = 0;
    
    //Object declaration
    BufferedReader reader;
    
    //Method to extract info into arrayList
    public void extractInfo(){
        
        try{
            reader = new BufferedReader (new FileReader ("extracted_log"));
            String input = reader.readLine();
            
           while(input != null){
               
                Matcher m = backfill.matcher(input);

                if (m.find()){
                    date.add(m.group(1));
                    time.add(m.group(2));
                    jobID.add(m.group(3));
                    cpuType.add(m.group(5));
                    cpuNum.add(m.group(7));
                    count ++;
                }
                
                input = reader.readLine();
            }
        }
        
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    
    // Method to display 
    public void display(){
        System.out.println("Table of Backfill");
        System.out.println();
        System.out.printf("%-15s %-20s %-15s %-15s %-10s \n","Date", "Time", "Job ID", "CPU/GPU Type", "CPU/GPU Num");
        System.out.println("+----------------------------------------------------------------------------+");
        for(int i=0; i<jobID.size(); i++){
            System.out.printf("%-15s %-20s %-15s %-15s %-10s \n",date.get(i), time.get(i), jobID.get(i), cpuType.get(i), cpuNum.get(i));
        }
        
        System.out.println();
        System.out.println("Total Backfill: " + count);
    }
    
    // Method to get total Job Killed
    public int getTotal(){
        return count;
    }
    
}
