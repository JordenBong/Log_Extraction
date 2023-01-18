import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;

public class ExcecutionTime {
    
    //IO object
    BufferedReader reader = null;
    
    //Pattern to check the line we want
    private Pattern allocate = Pattern.compile("\\[(.*?)\\] sched: Allocate JobId=(\\d+) .*");
    private Pattern done = Pattern.compile("\\[(.*?)\\] _job_complete: JobId=(\\d+) done");
    
    // Date Format Pattern
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
    
    //Months
    private String [] months = {"[2022-06", "[2022-07", "[2022-08", "[2022-09", "[2022-10", "[2022-11", "[2022-12"};
    
    
    // Variable to store total time in milliseconds
    long totalDuration = 0;
    int totalCountJob = 0;
    long [] durations = new long [7];
    int [] jobs = new int [7];
    
    // ArrayList to store JobID, start_time, end_time
    ArrayList <String> JobID_start = new ArrayList <String>(); 
    ArrayList <String> start_time = new ArrayList <String>();
    ArrayList <String> JobID_end = new ArrayList <String>();
    ArrayList <String> end_time = new ArrayList <String>();
    
    
    //Method to extract data into arraylist
    public void extractInfo(){
        
        try{
            reader = new BufferedReader (new FileReader ("extracted_log"));
            String input = reader.readLine();
            
           while(input != null){
               
                Matcher m = allocate.matcher(input);
                Matcher n = done.matcher(input);

                if (m.find()){
                    JobID_start.add(m.group(2));
                    start_time.add(m.group(1));
                }
                else if (n.find()){
                    JobID_end.add(n.group(2));
                    end_time.add(n.group(1));
                }
                
                input = reader.readLine();
            }
        }
        
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    
    //Method to calculate execution time
    public void calculate(){
        
        long difference=0;
        int count = 0;
        System.out.printf("%-10s %-15s %-15s %-30s %-30s %-15s \n", "Job ID", "Start Index", "End Index", "Start Time/Date", "End Time/Date", "Difference in milliseconds");
        System.out.println("+---------------------------------------------------------------------------------------------------------------------------------------------------------+");
        for (int j=0; j<JobID_start.size(); j++){
            String temp = JobID_start.get(j);
            int index = -1;
            
            //Search for the index used in list2 and time2
            for(int i=0; i<JobID_end.size(); i++){
                if(temp.equals(JobID_end.get(i))){
                    index = i;
                }
            }
            
            if(index == -1){
                continue;
            }
            
            //Date format
            try{
                Date d1 = sdf.parse(start_time.get(j));
                Date d2 = sdf.parse(end_time.get(index));
                
                //Calculate difference in between time1 and time2 with same list element (1/2)
                difference = d2.getTime() - d1.getTime();
                
                //System.out.println("Difference: " + difference);
                //Put into total
                totalDuration += difference;
            }  
            catch (ParseException e){
                e.printStackTrace();
            }
            
            System.out.printf("%-10s %-15s %-15s %-30s %-30s %-15d \n", JobID_start.get(j), j, index, start_time.get(j), end_time.get(index), difference);
            System.out.println();
            count ++;
        }
        
        System.out.println();
        System.out.println("Total: " + totalDuration + " milliseconds");
        System.out.println("Total Jobs that have been complete:" + count);
        double average = totalDuration / (double) count ;
        System.out.printf("Average job exceution time: %.2f milliseconds \n",average);
        
        long difference_In_Milliseconds = (long) average % 1000;
        long difference_In_Seconds = (long)(average / 1000) % 60;
        long difference_In_Minutes = (long)(average / (1000 * 60)) % 60;
        long difference_In_Hours = (long)(average / (1000 * 60 * 60)) % 24;
        System.out.printf("Average job exceution time: %s hours %s minutes %s seconds %s milliseconds \n",difference_In_Hours, difference_In_Minutes, difference_In_Seconds, difference_In_Milliseconds);
    }
}
