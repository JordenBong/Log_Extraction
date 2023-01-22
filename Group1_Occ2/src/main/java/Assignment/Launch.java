package Assignment;

import java.util.Scanner;

public class Launch
{
    public static String fileSource = "D:/Java/Group1_Occ2/src/main/java/Assignment/extracted_log.txt";
    public static void main(String[] args)
    {

        Scanner sc = new Scanner(System.in);
        System.out.print("""
                Please enter the data you want to call using keyword, reference as below:
                [Job created : A]
                [Job ended : B]
                [Job by partition : C]
                [Job causing error : D]
                [Average execution time of job : E]
                [Job Back Fill : F]
                [Job Exceed Time Limit : G]
                [Wexit Status : H]
                [Job Killed : I]
                [Total job created: J]
                [Job status : K]
                Keyword:\s""");
        char key = sc.next().charAt(0);
        System.out.println(" ");

        JobCreated jc = new JobCreated();
        jc.countJobCreated();
        JobEnded je = new JobEnded();
        je.countJobEnded();
        TotalJobCreated tjc = new TotalJobCreated();
        GetPartition gp = new GetPartition();
        Error error = new Error();
        ExcecutionTime et = new ExcecutionTime();
        et.extractInfo();
        JobBackFill jbf = new JobBackFill();
        Backfill bf = new Backfill();
        jbf.countBackFill();
        JobExceedTimeLimit jetl = new JobExceedTimeLimit();
        jetl.calculateJETL();
        WexitStatus ws = new WexitStatus();
        ws.extractInfo();
        ws.getList();
        WexitStatusSummary wss = new WexitStatusSummary();
        KillJob kj = new KillJob();
        kj.extractInfo();
        JobKill jk = new JobKill();
        JobStatus js = new JobStatus();


        switch (key)
        {
            case 'A' -> {
                jc.display();
                jbf.display();
            }
            case 'B' -> {
                je.display();
            }
            case 'C' -> {
                gp.displayByMonth();
                gp.calculatePartition();
                System.out.print("Please enter the month wanted to check(6-12): ");
                int monthGP = sc.nextInt();
                gp.displayPartition(monthGP);
            }
            case 'D' -> {
                error.displayError();
            }
            case 'E' -> {
                et.calculate();
            }
            case 'F' -> {
                bf.extractInfo();
                bf.display();
            }
            case 'G' -> {
                jetl.displayJETL();
            }
            case 'H' -> {
                ws.display();
                System.out.print("Do you want the summary of Wexit Status? (Y/N): ");
                char ask = sc.next().charAt(0);
                if (ask == 'Y')
                {
                    wss.grouping();
                    wss.showGrouping();
                }
            }
            case 'I' -> {
                kj.display();
                jk.countKill();
                jk.display();
            }
            case 'J' -> {
                tjc.display(jc.month, jc.countJobAllocate, jbf.countJobBackFill);
            }
            case 'K' -> {
                js.JobState();
                js.display();
            }
        }

    }
}

