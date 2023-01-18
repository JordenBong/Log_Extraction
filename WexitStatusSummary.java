import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Iterator;

public class WexitStatusSummary {
    
    //Pattern to extrace "Kill Job" data
    Pattern wexitstatus = Pattern.compile("\\[(.*?)T(.*?)\\] _job_complete: JobId=(\\d+) WEXITSTATUS (\\w+)");
    
    WexitStatus b = new WexitStatus();
 
    int[] total;
    
    //Object declaration
    BufferedReader reader;
    
    //Method to extract info to store into arraylist for further calculation
    public void grouping(){
        
        b.extractInfo();
        ArrayList <String> list = b.getList();
        Iterator <String> itr = list.iterator();
        total = new int [list.size()];
        
        try{
            reader = new BufferedReader (new FileReader ("extracted_log"));
            String input = reader.readLine();
            
           while(input != null){
               
                Matcher n = wexitstatus.matcher(input);

                if (n.find()){
                    //extarct info into arraylist
                    for(int i=0; i<list.size(); i++){
                        if (n.group(4).equals(list.get(i)))
                            total[i] ++;
                    }
                }
                input = reader.readLine();
            }
        }
        
        catch(IOException e){
            e.printStackTrace();
        } 
    }
    
    public void showGrouping(){
        b.extractInfo();
        ArrayList <String> list = b.getList();
        Iterator <String> itr = list.iterator();
        
        System.out.println("WEXITSTATUS Summary:");
        System.out.println("+------------------------------------------+");
        for(int i=0; i<list.size(); i++){
//            System.out.println("WEXITSTATUS " + list.get(i) + " --> " + total[i]);
            System.out.printf("%-12s %-4s %-5s %-5d \n", "WEXITSTATUS", list.get(i), "-->", total[i]);
        }
    }
    
    public ArrayList getList(){
        b.extractInfo();
        ArrayList <String> list = b.getList();
        Iterator <String> itr = list.iterator();
        return list;
    }
    
    public int[] getTotal(){
        return total;
    }
    
}
