package com.example.assignmentfop;

import java.io.FileInputStream;

import java.util.Scanner;
import java.io.FileNotFoundException;
public class JobStatus {
    public String[] jobState = {"Job Pending", "Job Running", "Job Timeout", "Job Cancelled", "Job Completed", "Job Failed"};
    public int[] num = new int[6];

    //Method to display list of Job State
    public void JobState() {
        try {
            Scanner inputStream = new Scanner(new FileInputStream("C:\\Users\\Yu Jia\\Desktop\\Assignment FOP\\extracted_log"));
            //initialize variable multiple array with size that known
            String submit[][] = new String[10552][5];
            String allocate[][] = new String[9216][7];
            String complete[][] = new String[8479][4];
            String kill[][] = new String[9216][7];
            String scedBack[][] = new String[750][9];
            String timeLimit[][] = new String[437][6];
            String exitStatus[][] = new String[8286][5];

            int s = 0, a = 0, c = 0, k = 0, b = 0, t = 0, f = 0, w = 0;
            while (inputStream.hasNextLine()) {
                //store job submit to submit multiple array submit
                String[] array = inputStream.nextLine().split(" ");
                if (array[1].equals("_slurm_rpc_submit_batch_job:")) {
                    //check with submit batchjob with job id
                    if (array[2].charAt(0) == 74)
                        if (array[3].charAt(0) == 73)
                            if (array[4].charAt(0) == 117) {
                                for (int j = 0; j < array.length; j++)
                                    submit[s][j] = array[j];
                                s++;
                            }
                }
                //store job sched allocate to multiple array allocate
                if (array[1].equals("sched:")) {
                    if (array[2].equals("Allocate")) {
                        for (int j = 0; j < array.length; j++) {
                            allocate[a][j] = array[j];
                        }
                        a++;

                    }
                }
                //store job time limit exhausted into multiple array timeLimit
                if (array[1].equals("Time")) {
                    if (array[2].equals("limit"))
                        if (array[3].equals("exhausted"))

                            for (int j = 0; j < array.length; j++) {
                                timeLimit[t][j] = array[j];
                            }
                    t++;


                }
                //store job complete into multiple array complete
                if (array[1].equals("_job_complete:")) {
                    if (array[3].equals("done")) {
                        for (int e = 0; e < array.length; e++) {
                            complete[c][e] = array[e];
                        }
                        c++;
                    }
                }
                //store job exitstatus into multiple array exitStatus
                if (array[1].equals("_job_complete:")) {
                    if (array[3].equals("WEXITSTATUS")) {
                        for (int e = 0; e < array.length; e++) {
                            exitStatus[w][e] = array[e];
                        }
                        w++;
                    }
                }

                //store job kill into multiple array kill
                if (array[1].equals("_slurm_rpc_kill_job:")) {
                    if (array[2].equals("REQUEST_KILL_JOB")) {
                        for (int j = 0; j < array.length; j++) {
                            kill[k][j] = array[j];
                        }
                        k++;

                    }
                }
                //store job backfill into multiplearray scedBack
                if (array[1].equals("sched/backfill:")) {
                    if (array[2].equals("_start_job:")) {
                        for (int j = 0; j < array.length; j++) {
                            scedBack[b][j] = array[j];
                        }
                        b++;

                    }
                }
            }
            //initialize array
            String[] jobId = new String[10552];
            String[] timeAllocate = new String[10552];
            String[] timeComplete = new String[10552];
            String[] status = new String[10552];

            //store jobid that submit into array jobId and initialize all jobId with state Pending
            for (int p = 0; p < submit.length; p++) {
                jobId[p] = submit[p][2];
                status[p] = ("Pending");

            }

            //compare jobId with job that allocate, if job is allocate will change state to Running
            for (int p = 0; p < submit.length; p++) {
                for (int r = 0; r < allocate.length; r++)
                    if (jobId[p].equals(allocate[r][3])) {
                        timeAllocate[p] = allocate[r][0];
                        status[p] = ("Running");

                    }
            }
            //compare jobId with job that scedback, if job is scedback also change state into running
            for (int p = 0; p < submit.length; p++) {
                for (int r = 0; r < scedBack.length; r++)
                    if (jobId[p].equals(scedBack[r][4])) {
                        timeAllocate[p] = scedBack[r][0];
                        status[p] = ("Running");

                    }
            }

            //compare jobId with time limit change state to Timeout
            for (int p = 0; p < jobId.length; p++) {
                for (int r = 0; r < timeLimit.length; r++)
                    if (jobId[p].equals(timeLimit[r][5])) {
                        status[p] = ("Timeout");

                    }
            }
            //compare jobId with kill change state to cancelled
            for (int p = 0; p < jobId.length; p++) {
                for (int r = 0; r < kill.length; r++)
                    if (jobId[p].equals(kill[r][3])) {
                        status[p] = ("Cancelled");
                    }

            }
            //compare jobId with complete change state to complete
            for (int p = 0; p < jobId.length; p++) {
                for (int r = 0; r < complete.length; r++)
                    if (jobId[p].equals(complete[r][2])) {
                        timeComplete[p] = complete[r][0];
                        status[p] = ("Completed");

                    }
            }

            //compare jobId with exitStatus if exit with nonzero change state to Failed
            for (int p = 0; p < jobId.length; p++) {
                for (int r = 0; r < exitStatus.length; r++)
                    if (jobId[p].equals(exitStatus[r][2])) {
                        if (Integer.parseInt(exitStatus[r][4]) != 0) {
                            status[p] = ("Failed");
                        }
                    }

            }

            //display all the jobid with state
            System.out.printf("%-15s%-30s%-30s%10s", "Job Id", "Start", "End", "State" + "\n");
            System.out.println("+------------+----------------------------+------------------------------+---------+");
            for (int p = 0; p < 10552; p++) {
                System.out.printf("%-15s%-30s%-30s%10s", jobId[p], timeAllocate[p], timeComplete[p], status[p] + "\n");
            }


            for (int q = 0; q < status.length; q++) {
                if (status[q].equals("Pending"))
                    num[0]++;
                else if (status[q].equals("Running"))
                    num[1]++;
                else if (status[q].equals("Timeout"))
                    num[2]++;
                else if (status[q].equals("Cancelled"))
                    num[3]++;
                else if (status[q].equals("Completed"))
                    num[4]++;
                else if (status[q].equals("Failed"))
                    num[5]++;
            }

        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("fail");
        }

    }
    //display table of jobState
    public void display(){
        System.out.println("+-------------+------------------+");
        System.out.printf("%-15s%15s", "Job State", "Number of job" + "\n");
        System.out.println("+-------------+------------------+");
        for (int j = 0; j < num.length; j++) {
            System.out.printf("%-15s%8d", jobState[j], num[j]);
            System.out.println("");
        }
    }
}






