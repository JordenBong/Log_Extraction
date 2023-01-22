/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Assignment;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Error {

    public void displayError() {
        System.out.println("User               No. of time error");
        System.out.println("------------------------------------");
 
        String[] userList = getUser(extractError());
        accumulate(userList);
        
        System.out.println("------------------------------------");
        System.out.printf("Total error:%17d\n",totalError(userList));
        
    }
    
    public static int totalError (String[] userList){
        int totalError = 0;
        for(int i = 0; i< userList.length; i++){
            totalError++;
        }
        return totalError;
    }
    
    
    public static String[] extractError(){
        File file = new File(Launch.fileSource);
        String word = "error: This association";
        List<String> listOfError = new ArrayList<String>();
 
    try {
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
        String line = sc.nextLine();
            if(line.contains(word)) {
                listOfError.add(line);
            }
        }
    } 
    
    catch(FileNotFoundException e) { 
        System.out.println("File was not found.");
    }
    
    String[] errorList = listOfError.toArray(new String[0]);
    
    return errorList; 
    }
           
    public static String[] getUser(String[] errorList){
        List<String> listOfUser = new ArrayList<String>();
        for(int i = 0; i<errorList.length;i++){
            Pattern pattern = Pattern.compile("^(.*)user='([a-zA-Z_0-9.]+)(.*)");
            Matcher matcher = pattern.matcher(errorList[i]);
            while(matcher.find()){
                listOfUser.add(matcher.group(2));
            }
        }
        String[] userList = listOfUser.toArray(new String[0]);
        
        return userList; 
    }
   public static void acc (){

   }
    public static void accumulate(String[] userList){ 
        int []totalUser = new int[userList.length];

        //sort in ascending order
        for(int pass = 1; pass<userList.length; pass++)
            for (int i = 0; i < userList.length - 1; i++)
                if (userList[i].compareTo(userList[i + 1]) > 0) {
                    String hold = userList[i];
                    userList[i] = userList[i + 1];
                    userList[i + 1] = hold;
                }


            //accumulate
            int index = 0;
            String[] userExtracted = new String[userList.length];
            for (int i = 0; i < userList.length - 1; i++) {
                if (userList[i].equals(userList[i + 1])) {
                    totalUser[index]++;
                    continue;
                }

                totalUser[index]++;
                userExtracted[index] = userList[i];
                System.out.printf("%-25s %-5s\n", userExtracted[index], totalUser[index]);
                index++;
            }
    }

}


