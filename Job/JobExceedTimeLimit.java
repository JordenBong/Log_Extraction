import java.io.*;
import java.util.*;
import java.util.regex.*;

public class JobExceedTimeLimit {
    
    Pattern exceedLimit = Pattern.compile("\\[(.*?)T(.*?)\\] Time limit exhausted for JobId=(\\d+)");
    BufferedReader reader = null;
    private int [] JETL = new int [7];
    private String [] months = {"[2022-06", "[2022-07", "[2022-08", "[2022-09", "[2022-10", "[2022-11", "[2022-12"};
    private String [] monthsWords = {"June", "July", "August", "September", "October", "November", "December"};
    private int totalJETL = 0;
    
    /**
     * Calculate Job Exceed Time Limit(JETL) based on month
     */
    public void calculateJETL(){

        try{
            reader = new BufferedReader (new FileReader("extracted_log"));
            String input = reader.readLine();

            for(int i = 0; i<7; i++){
                Matcher g ;
                int count = 0;

                while (input != null){
                    g = exceedLimit.matcher(input);
                    if(g.find()){
                        if(input.contains(months[i])) {count++;}
                        else {break;}
                    }
                    input = reader.readLine();
                }
                JETL[i] = count;
                totalJETL += count;
            }
            System.out.println("Total Job Exceeding Time Limit have been calculated successfully.");
            System.out.println();
            reader.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File is not found.");
        }
        catch(IOException e){
            System.out.println("Exception occurs.");
        }
    }
    
    public void displayJETL(){
        System.out.println("Total Job Exceeding Time Limit from June to December 2022: ");
        System.out.println("+------------------------------------------------------------+");
        System.out.println("Total --> " + totalJETL);
        System.out.println();
        System.out.println("Months Summary");
        System.out.println("+-----------------------------------+");
        
        for(int j=0; j<7; j++){
            System.out.println(monthsWords[j] + " --> " + JETL[j]);
        }
    }
    
    
    /**
     * Obtain JETL based on month 
     * @param num 
     */
    public int getJETL(int num){
        return JETL[num-6];
    }
    
    
}
