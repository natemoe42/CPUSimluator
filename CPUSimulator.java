/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
/**
 *
 * @author natemoe
 */
public class CPUSimulator 
{
    private static int idleTime = 1;
    private static int timeSlice = 1;
    private static int busyTime = 0;
    private static double avgWaitTime = 0;
    private static ReadyQueue queue;
    private static ArrayList<String> defaultList = new ArrayList<>();
    private static ArrayList<Job> getSummary = new ArrayList<>();
        
    public CPUSimulator()
    {
        queue =  new ReadyQueue(new SRTComparator());
        defaultList = new ArrayList<>(Arrays.asList("no new job this slice", "no new job this slice", "add job Job_A with length 2",
        "no new job this slice", "no new job this slice", "no new job this slice", "no new job this slice", "add job Job_B with length 7", 
        "no new job this slice", "add job Job_C with length 4"));
    }
    
    public CPUSimulator(Comparator<Job> comparator)
    {
        queue = new ReadyQueue(comparator);
        defaultList = new ArrayList<>(Arrays.asList("no new job this slice", "no new job this slice", "add job Job_A with length 2",
                "no new job this slice", "no new job this slice", "no new job this slice", "no new job this slice", "add job Job_B with length 7",
                "no new job this slice", "add job Job_C with length 4"));
    }

    public CPUSimulator(String inFileName, Comparator<Job> comparator)
    {
        queue = new ReadyQueue(comparator);
        readFromFile(inFileName);
    }
    
    public CPUSimulator(String inFileName, String outFileName, Comparator<Job> comparator)
    {
        queue = new ReadyQueue(comparator);
        readFromFile(inFileName);
        saveToFile(outFileName);
    }
  
    
    
    public static void start()
    {      
        introduction();        
        while (!isFinished()) 
        {
            System.out.println("\nTime slice: " + timeSlice);
            if(!defaultList.isEmpty())
            {
                String test = defaultList.remove(0);
                if(test.equalsIgnoreCase("no new job this slice"))
                {
                    System.out.println("\tCommand read: \"" + test + "\"");
                }
                else
                {
                    System.out.println("\tCommand read: \"" + test + "\"");
                    String[] test2 = test.split(" ");
                    Job jobs = new Job(test2[2], Integer.parseInt(test2[5]));
                    queue.add(jobs);
                    jobs.createdTime = timeSlice;
                    System.out.println("\tAdding to queue:\t[Name:" + jobs.name + " Length:" + jobs.length + " Execution:" + jobs.executionTime + " Remaining:" + (jobs.length - jobs.executionTime) + " Wait:" + jobs.waitTime + "]");
                }
            }
            else
            {
                System.out.println("\tCommand read: \"no new job this slice\"");
            }
            
            if(queue.isEmpty())
            {
                System.out.println("\tReady queue empty, nothing to execute, CPU idle for " + idleTime + " continuous time slices");
            }
            
            if(!queue.isEmpty())
            {
                idleTime = 1;
                busyTime++;
                Job removed = queue.remove();
                System.out.println("\tRemoved from queue:\t[Name:" + removed.name + " Length:" + removed.length + " Execution:" + removed.executionTime + " Remaining:" + (removed.length - removed.executionTime) + " Wait:" + removed.waitTime + "]");
                System.out.println("\tExecuting:\t\t[Name:" + removed.name + " Length:" + removed.length + " Execution:" + removed.executionTime + " Remaining:" + (removed.length - removed.executionTime) + " Wait:" + removed.waitTime + "]");
                executeJob(removed);
                queue.increaseWaitTime();
                if(removed.executionTime != removed.length)
                {
                    queue.add(removed);
                    System.out.println("\tAdding to queue:\t[Name:" + removed.name + " Length:" + removed.length + " Execution:" + removed.executionTime + " Remaining:" + (removed.length - removed.executionTime) + " Wait:" + removed.waitTime + "]");
                }
                else
                {
                    avgWaitTime = removed.waitTime;
                    getSummary.add(removed);
                    removed.finishTime = timeSlice;
                    System.out.println("\tCompleted:\t\t[Name:" + removed.name + " Length:" + removed.length + " Execution:" + removed.executionTime + " Remaining:" + (removed.length - removed.executionTime) + " Wait:" + removed.waitTime + "]");
                }
            }
            else
            {
                idleTime++;
            }
            timeSlice++;   
        }       
        summaryReport();
        
    }
    private static void saveToFile(String outFileName)
    {
        try 
        {
            PrintStream out = new PrintStream(new FileOutputStream(outFileName, true), true);
            System.setOut(out);
        } 
        catch (IOException e) 
        {
            System.out.println("An error occurred while trying to save the file: " + e.getMessage());
        }
    }
    
    private static void readFromFile(String inFileName)
    {
            try 
            {
                FileReader fileReader = new FileReader(inFileName);

                try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                    String line;
                    while ((line = bufferedReader.readLine()) != null)
                    {
                        defaultList.add(line);
                    }
                }
            } 
            catch (IOException e) 
            {
                System.err.println("Error reading file: " + e.getMessage());
            }
    }
    
    private static boolean isFinished()
    {
        return idleTime > 50;
    }
    
    private static void executeJob(Job job)
    {
        if(job.executionTime == 0)
        {
            job.startTime = timeSlice;
        }        
        job.executionTime++;
        job.RRsequence = Job.RRMaster++;
    }
    
    private static void introduction()
    {        
        Comparator name = queue.comparator();        
        System.out.println("Starting CPU Simulation with " + name.toString()); 
    }
    
    private static void summaryReport()
    {
        Comparator name = queue.comparator();
        double x = busyTime * 100;
        double y = timeSlice - 51;
        System.out.println("\nCPU idle for 50 time slices. Shutting down.");
        System.out.println("\n----- Summary -----");
        System.out.println("CPU Simulator with " + name.toString());
        System.out.println("\nSimulation Run Time: " + (timeSlice - 51));
        System.out.println("CPU Idle Time: " + (timeSlice - 51 - busyTime));
        System.out.println("CPU Busy Time: " + busyTime);
        System.out.print("CPU Utilization: ");
        System.out.printf("%,.1f", x / y);
        System.out.print("%\nAverage Wait Time: ");
        System.out.printf("%,.1f\n", avgWaitTime / getSummary.size());
        

        System.out.println("\nList of Jobs in Order of Completion");
        for(int i = 0; i < getSummary.size();)
        {
            Job job = getSummary.remove(i);
            double waitTime = job.waitTime;
            double length = job.length;
            System.out.println("Job " + job.name);
            System.out.println("\tLength: " + job.length);
            System.out.println("\tStart Time: " + job.startTime);
            System.out.println("\tFinish Time: " + job.finishTime);
            System.out.println("\tExecution Time: " + job.executionTime);
            System.out.println("\tWait Time: " + job.waitTime);
            System.out.print("\tResponse Ratio: "); 
            System.out.printf("%,.1f\n\n", ((waitTime + length) / length));
        }
    }
}
