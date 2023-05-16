/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author natemoe
 */
public class Job 
{
    String name;
    int length;
    int startTime;
    int finishTime;
    int executionTime;
    int waitTime;
    long RRsequence;
    int createdTime;
    static long RRMaster = Long.MIN_VALUE;
    
    public Job(String name, int length)
    {
        this.name = name;
        this.length = length;
        this.startTime = 0;
        this.finishTime = 0;
        this.executionTime = 0;
        this.waitTime = 0;
        this.RRsequence = RRMaster++;
        this.createdTime = 0;
    }
}
