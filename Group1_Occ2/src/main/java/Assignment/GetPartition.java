package Assignment;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class GetPartition {
    
        Pattern allocate = Pattern.compile("\\[(.*?)T(.*?)\\] sched: Allocate JobId=(\\d+) NodeList=(.*?) #CPUs=(\\d+) Partition=(.*)");
        BufferedReader reader = null;
        private int [] counts = new int [7];
        private int [] countEPYCs = new int [7];
        private int [] countOpterons = new int [7];
        private int [] countGPUs = new int [7];
        private String [] months = {"[2022-06", "[2022-07", "[2022-08", "[2022-09", "[2022-10", "[2022-11", "[2022-12"};
        private String [] monthsWords = {"June", "July", "August", "September", "October", "November", "December"};
        
        /**
         * Display Task "Allocate" based on months in table form
         */
        public void displayByMonth(){
            
            try{
                reader = new BufferedReader (new FileReader(Launch.fileSource));
                String input = reader.readLine();
                for(int i = 0; i<7; i++){
                    
                    Matcher m ;
                    System.out.println(monthsWords[i] +" Allocate Task Summary");
                    System.out.println("--------------------------------------------------------------------------------------------------------------------");
                    System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s \n", "Date", "Time", "JobID", "Node List", "CPUs", "Partition");
                    while (input != null){
                        m = allocate.matcher(input);
                        if(m.find()){
                            if(input.contains(months[i]))
                                System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s \n",m.group(1), m.group(2), m.group(3), m.group(4), m.group(5), m.group(6));
                            else
                                break;
                        }
                        input = reader.readLine();
                    }                           
                    System.out.println();
                    System.out.println("+------------------------------------------------------------------------------------------------------------------+");
                    System.out.println();
                }
            }
            catch(FileNotFoundException e){
                System.out.println("File is not found.");
            }
            catch(IOException e){
                System.out.println("Exception occurs.");
            }

        }

            /**
             * Enter which month to display the number of partition in that specific month
             * @param num
             */
        public void displayPartition(int num){
          
            System.out.println(monthsWords[num-6] + " Partition Summary: ");
            System.out.println("+-----------------------------------+");
            System.out.println("Number of Job Being Allocated: " + counts[num-6]);
            System.out.println("Partition EPYC: " + countEPYCs[num-6]);
            System.out.println("Partition Opteron: " + countOpterons[num-6]);
            System.out.println("Partition GPU: " + countGPUs[num-6]);
        }
            
        /**
         * Display Partition based on month
         */
        public void displayPartition(){
            for(int j=0; j<7; j++){
                System.out.println(monthsWords[j] + " Partition Summary: ");
                System.out.println("+-----------------------------------+");
                System.out.println("Number of Job Being Allocated: " + counts[j]);
                System.out.println("Partition EPYC: " + countEPYCs[j]);
                System.out.println("Partition Opteron: " + countOpterons[j]);
                System.out.println("Partition GPU: " + countGPUs[j]);
                System.out.println();
            }
        }

        /**
         * Calculate Partition based on month
         */
        public void calculatePartition(){
            
            try{
                reader = new BufferedReader (new FileReader("D:/Java/Assignment 1002/src/extracted_log.txt"));
                String input = reader.readLine();
                
                for(int i = 0; i<7; i++){
                    Matcher n ;
                    int count = 0;
                    int countEPYC = 0;
                    int countOpteron = 0;
                    int countGPU = 0;

                    while (input != null){
                        n = allocate.matcher(input);
                        if(n.find()){
                            if(input.contains(months[i])){}
                            else{break;}

                            if(n.group(6).contains("opteron"))
                                countOpteron ++;
                            else if(n.group(6).contains("epyc"))
                                countEPYC ++;
                            else if(n.group(6).contains("gpu"))
                                countGPU ++;

                        }
                        input = reader.readLine();
                    }
                    counts[i] = countOpteron + countEPYC + countGPU;
                    countOpterons[i] = countOpteron;
                    countEPYCs[i] = countEPYC;
                    countGPUs[i] = countGPU;
                }
                System.out.println("Partition calculate successfully!");
                reader.close();
            }
            catch(FileNotFoundException e){
                System.out.println("File is not found.");
            }
            catch(IOException e){
                System.out.println("Exception occurs.");
            }
        }
        
        //Getter
        public int getPartition(String str, int month){
            if(str.equals("epyc")) return countEPYCs[month-6];
            else if (str.equals("gpu")) return countGPUs[month-6];
            else if (str.equals("opteron")) return countOpterons[month-6];
            else return 0;
        }
}