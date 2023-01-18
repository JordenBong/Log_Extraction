import java.io.*;
import java.util.*;
import java.util.regex.*;

public class KillJob {
    
    //Pattern to extrace "Kill Job" data
    Pattern kill = Pattern.compile("\\[(.*?)T(.*?)\\] _slurm_rpc_kill_job: REQUEST_KILL_JOB JobId=(\\d+) uid (\\d+)");
    
    //Array List to store killed job
    private ArrayList <String> date = new ArrayList <String>();
    private ArrayList <String> time = new ArrayList <String>();
    private ArrayList <String> jobID = new ArrayList <String>();
    private ArrayList <String> userID = new ArrayList <String>();
    
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
               
                Matcher m = kill.matcher(input);
                
                if (m.find()){
                    date.add(m.group(1));
                    time.add(m.group(2));
                    jobID.add(m.group(3));
                    userID.add(m.group(4));
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
        System.out.println("Table of Request Kill Job");
        System.out.println();
        System.out.printf("%-15s %-20s %-15s %-15s \n","Date", "Time", "Job ID", "User ID");
        System.out.println("+----------------------------------------------------------------------------+");
        for(int i=0; i<jobID.size(); i++){
            System.out.printf("%-15s %-20s %-15s %-15s \n",date.get(i), time.get(i), jobID.get(i), userID.get(i));
        }
        
        System.out.println();
        System.out.println("Total Jobs Being Killed: " + count);
    }
    
    // Method to get total Job Killed
    public int getTotal(){
        return count;
    }
    
}
